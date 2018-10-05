package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Properties;

/**
 * VacancyDao
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class VacancyDao implements AutoCloseable {

    /**
     * logger
     */
    private static final Logger LOG = LogManager.getLogger(VacancyDao.class.getName());

    /**
     * properties
     */
    private final Properties properties;

    /**
     * db connection
     */
    private final Connection connection;

    public VacancyDao(Properties properties) {
        this.properties = properties;
        this.connection = getConnection();
        createTableVacancy();
        createTableLastUpdate();
    }

    /**
     * Method getConnection
     * @return connection
     */
    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(properties.getProperty("jdbc.url"));
            LOG.info("get connection", connection.getClientInfo());
        } catch (SQLException e) {
            LOG.error("Error get connection", e);
        }
        return connection;
    }

    /**
     * Method createTableVacancy
     */
    private void createTableVacancy() {
        String sql = "create table if not exists vacancy("
                + "id integer primary key autoincrement not null,"
                + "title text,"
                + "link text,"
                + "author text, "
                + "date numeric);";
        try (Statement statement = connection.createStatement()) {
            LOG.info("create table vacancy ", statement.toString());
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            LOG.error("Error create table vacancy", e);
        }
    }

    /**
     * Method createTableLastUpdate
     */
    private void createTableLastUpdate() {
        String sql = "create table if not exists last_update(date numeric);";
        try (Statement statement = connection.createStatement()) {
            LOG.info("create table last_update ", statement.toString());
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            LOG.error("Error create table last_update", e);
        }
    }

    /**
     * Method insert
     */
    public void insert(Vacancy vacancy) {
        String sql = "insert into vacancy(title, link, author, date) values(?,?,?,?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vacancy.getTitle());
            statement.setString(2, vacancy.getLink());
            statement.setString(3, vacancy.getAuthor());
            statement.setDate(4, new Date(vacancy.getDate().getTime()));
            LOG.info("insert vacancy ", statement.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Error insert vacancy", e);
        }
    }

    /**
     * Method insertSet
     */
    public void insertSet(Set<Vacancy> vacancies) {
        String sql = "insert into vacancy(title, link, author, date) values(?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            for (Vacancy vacancy : vacancies) {
                statement.setString(1, vacancy.getTitle());
                statement.setString(2, vacancy.getLink());
                statement.setString(3, vacancy.getAuthor());
                statement.setDate(4, new Date(vacancy.getDate().getTime()));
                statement.addBatch();
            }
            LOG.info("insert vacancies set", statement.toString());
            statement.executeBatch();
            connection.commit();
        } catch (SQLException sqle) {
            LOG.error("Error insert set vacancies", sqle);
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOG.error("Error connection rollback", e);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOG.error("Error set autocommit or close connection", e);
            }
        }
    }

    /**
     * Method delete
     */
    public void delete(Vacancy vacancy) {
        String sql = "delete from vacancy where link = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vacancy.getLink());
            LOG.info("delete vacancy ", vacancy.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Error delete vacancy", e);
        }
    }

    /**
     * Method getById
     */
    public Vacancy getById(int id) {
        Vacancy vacancy = null;
        String sql = "select id, title, link, author, date from vacancy where id = ?;";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            LOG.info("get vacancy by id ", id);
            resultSet.next();
            int vacancyId = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String link = resultSet.getString("link");
            String author = resultSet.getString("author");
            java.util.Date date = resultSet.getDate("date");
            vacancy = new Vacancy(vacancyId, title, link, author, date);
        } catch (SQLException e) {
            LOG.error("Error getById vacancy", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LOG.error("Error result set close", e);
                }
            }
        }
        return vacancy;
    }

    /**
     * Method getAll
     */
    public List<Vacancy> getAll() {
        String sql = "select id, title, link, author, date from vacancy;";
        List<Vacancy> vacancies = new ArrayList<>();
        ResultSet resultSet = null;
        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(sql);
            LOG.info("get all vacancies");
            while (resultSet.next()) {
                int vacancyId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String link = resultSet.getString("link");
                String author = resultSet.getString("author");
                java.util.Date date = resultSet.getDate("date");
                vacancies.add(new Vacancy(vacancyId, title, link, author, date));
            }
        } catch (SQLException e) {
            LOG.error("Error get all vacancies", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LOG.error("Error result set close", e);
                }
            }
        }
        return vacancies;
    }

    /**
     * Method deleteAll
     */
    public void deleteAll() {
        String sql = "delete from vacancy;";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            LOG.error("Error delete all vacancies ", e);
        }
    }

    /**
     * Method insertLastUpdateDate
     */
    public void insertLastUpdateDate(long date) {
        String sql = "insert into last_update (date) values (?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new Date(date));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Error insert last update date", e);
        }
    }

    /**
     * Method updateLastUpdateDate
     */
    public void updateLastUpdateDate(long date) {
        String sql = "update last_update set date = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new Date(date));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Error update last update date", e);
        }
    }

    /**
     * Method getLastUpdateDate
     */
    public java.util.Date getLastUpdateDate() {
        java.util.Date result = null;
        String sql = "select date from last_update;";
        ResultSet resultSet = null;
        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            result = resultSet.getDate("date");
        } catch (SQLException e) {
            LOG.error("Error get last update date", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOG.error("Error close result set", e);
            }
        }
        return result;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
