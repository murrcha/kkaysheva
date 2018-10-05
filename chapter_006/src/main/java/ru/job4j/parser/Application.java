package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;

/**
 * Application
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Application implements Job {

    /**
     * logger
     */
    private static final Logger LOG = LogManager.getLogger(Application.class.getName());

    /**
     * base url
     */
    private static final String URL = "http://www.sql.ru/forum/job-offers/";

    /**
     * default config
     */
    private static final String DEFAULT_CONFIG = "app.properties";

    /**
     * set vacancies
     */
    private final Set<Vacancy> vacancies = new HashSet<>();

    /**
     * vacancy parser
     */
    private final VacancyParser parser = new VacancyParser();

    /**
     * properties
     */
    private final Properties properties;

    public Application(Properties properties) {
        this.properties = properties;
    }

    /**
     * Method firstRunning - run code for first start application
     */
    private void firstRunning() {
        for (int page = 1; page < 50; page++) {
            vacancies.addAll(parser.parseHtml(URL + page, null));
        }
        LOG.info(vacancies.size());
        try (VacancyDao vacancyDao = new VacancyDao(properties)) {
            vacancyDao.insertSet(vacancies);
            vacancyDao.insertLastUpdateDate(new Date().getTime());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Method nextRunning - run code for next start application
     */
    private void nextRunning(Date date) {
        for (int page = 1; page < 10; page++) {
            vacancies.addAll(parser.parseHtml(URL + page, date));
        }
        LOG.info(vacancies.size());
        try (VacancyDao vacancyDao = new VacancyDao(properties)) {
            vacancyDao.insertSet(vacancies);
            vacancyDao.updateLastUpdateDate(new Date().getTime());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Method getLastUpdate date
     * @param properties properties
     * @return date
     */
    private Date getLastUpdate(Properties properties) {
        Date lastUpdate = null;
        try (VacancyDao vacancyDao = new VacancyDao(properties)) {
            lastUpdate = vacancyDao.getLastUpdateDate();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return lastUpdate;
    }

    /**
     * Method main
     * @param args properties file
     */
    public static void main(String[] args) {
        String config = DEFAULT_CONFIG;
        if (args.length == 0) {
            LOG.info("load default properties");
        } else {
            config = args[0];
            LOG.info("load properties from args");
        }
        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = loader.getResourceAsStream(config)) {
            properties.load(is);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        Application app = new Application(properties);
        Date lastUpdate = app.getLastUpdate(properties);
        if (lastUpdate == null) {
            app.firstRunning();
        } else {
            app.nextRunning(lastUpdate);
        }
        String cronExp = properties.getProperty("cron.time");
        SchedulerFactory factory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = factory.getScheduler();
            scheduler.start();
            JobDetail job = JobBuilder.newJob(Application.class).build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronExp))
                    .build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        LOG.info("Execute schedule task");
        Date lastUpdate = getLastUpdate(properties);
        nextRunning(lastUpdate);
    }
}
