package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.job4j.parser.pojo.Vacancy;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * VacancyParser
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class VacancyParser {

    /**
     * logger
     */
    private static final Logger LOG = LogManager.getLogger(VacancyParser.class.getName());

    /**
     * reg exp for search vacancy contains text "java" or "Java"
     */
    private static final String REGEX = "[Jj]ava(?!(Script|script| Script| script)).*";

    /**
     * tags, text, css for parsing
     */
    private static final String TODAY = "сегодня";
    private static final String YESTERDAY = "вчера";
    private static final int MINUS_DAY = -1;
    private static final String DATE_FORMAT = "dd MMM yy, HH:mm";
    private static final String TR_TAG = "tr";
    private static final String TD_TAG = "td";
    private static final String B_TAG = "b";
    private static final String A_TAG = "a";
    private static final String HREF_ATTR = "href";
    private static final String TABLE_SORT_OPTIONS = "table.sort_options";
    private static final String TABLE_FORUM_TABLE = "table.forumTable";
    @SuppressWarnings("SpellCheckingInspection")
    private static final String TD_POSTSLISTTOPIC = "td.postslisttopic";
    @SuppressWarnings("SpellCheckingInspection")
    private static final String TD_ALTCOL = "td.altCol";

    /**
     * set vacancies
     */
    private final Set<Vacancy> vacancies = new HashSet<>();

    /**
     * Method parseStringToDate - convert string date to Date type
     *
     * @param date string
     * @return Date result
     */
    private Date parseStringToDate(String date) {
        Date resultDate = null;
        if (date.contains(TODAY) || date.contains(YESTERDAY)) {
            String[] dateStrings = date.split(",");
            if (dateStrings[0].equalsIgnoreCase(TODAY)) {
                Calendar today = Calendar.getInstance();
                resultDate = today.getTime();
            } else if (dateStrings[0].equalsIgnoreCase(YESTERDAY)) {
                Calendar yesterday = Calendar.getInstance();
                yesterday.add(Calendar.DATE, MINUS_DAY);
                resultDate = yesterday.getTime();
            }
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            try {
                resultDate = formatter.parse(date);
            } catch (ParseException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return resultDate;
    }

    /**
     * Method isCurrentYear - current year in date or not
     *
     * @param date date for check
     * @return result true or false
     */
    private boolean isCurrentYear(Date date) {
        boolean result = false;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar today = Calendar.getInstance();
        if (today.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
            result = true;
        }
        return result;
    }

    /**
     * Method getCurrentPage - get current page from html
     *
     * @param url string
     * @return page number
     */
    public int getCurrentPage(String url) {
        Document doc;
        int result = 0;
        try {
            doc = Jsoup.connect(url).get();
            String position = doc.body().select(TABLE_SORT_OPTIONS)
                    .last()
                    .select(TR_TAG)
                    .select(TD_TAG)
                    .select(B_TAG)
                    .text();
            result = Integer.parseInt(position);
        } catch (IOException e) {
            LOG.error(e, e.fillInStackTrace());
        }
        return result;
    }

    /**
     * Method parseHtml - parse html page
     *
     * @param url string
     * @return vacancy set
     */
    public Set<Vacancy> parseHtml(String url, Date lastUpdate) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            LOG.error(e, e.fillInStackTrace());
        }
        List<Element> rows = Objects.requireNonNull(doc).body().select(TABLE_FORUM_TABLE).first().select(TR_TAG);
        for (Element row : rows) {
            String title = row.children().select(TD_POSTSLISTTOPIC).text();
            String link = "";
            String authorCol;
            String dateCol;
            Date date;
            if (Pattern.matches(REGEX, title)) {
                LOG.info("title contains java!");
                LOG.info(title);
                dateCol = row.children().select(TD_ALTCOL).last().text();
                LOG.info(String.format("date: %s", dateCol));
                date = parseStringToDate(dateCol);
                if (lastUpdate != null && (date.compareTo(lastUpdate) < 0)) {
                    break;
                }
                if (isCurrentYear(date)) {
                    List<Element> links = row.select(TD_POSTSLISTTOPIC).select(A_TAG);
                    if (!links.isEmpty()) {
                        link = links.get(0).attr(HREF_ATTR);
                        LOG.info(String.format("link: %s", link));
                    }
                    authorCol = row.children().select(TD_ALTCOL).select(A_TAG).text();
                    LOG.info(String.format("author: %s", authorCol));
                    vacancies.add(new Vacancy(title, link, authorCol, date));
                }
            }
        }
        return vacancies;
    }
}
