package ru.job4j.parser.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.parser.pojo.Vacancy;

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
import java.util.Iterator;
import java.util.Properties;

/**
 * VacancyDaoImpl
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class VacancyDaoImpl implements VacancyDao {

    /**
     * logger
     */
    private static final Logger LOG = LogManager.getLogger(VacancyDaoImpl.class.getName());

    /**
     * properties
     */
    private Properties properties;

    public VacancyDaoImpl(Properties properties) throws VacancyDaoException {
        this.properties = properties;
        createTableVacancy();
        createTableLastUpdate();
    }

    /**
     * Method getConnection
     *
     * @return connection
     * @throws VacancyDaoException exception
     */
    private Connection getConnection() throws VacancyDaoException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(properties.getProperty("jdbc.url"));
            LOG.info("get connection", connection.getClientInfo());
        } catch (SQLException e) {
            LOG.error("Error get connection", e);
            throw new VacancyDaoException("Error get connection", e);
        }
        return connection;
    }

    /**
     * Method createTableVacancy
     *
     * @throws VacancyDaoException exception
     */
    private void createTableVacancy() throws VacancyDaoException {
        String sql = "create table if not exists vacancy("
                + "id integer primary key autoincrement not null,"
                + "title text,"
                + "link text,"
                + "author text, "
                + "date numeric);";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()
        ) {
            LOG.info("create table vacancy ", statement.toString());
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            LOG.error("Error create table vacancy", e);
            throw new VacancyDaoException("Error create table vacancy", e);
        }
    }

    /**
     * Method createTableLastUpdate
     *
     * @throws VacancyDaoException exception
     */
    private void createTableLastUpdate() throws VacancyDaoException {
        String sql = "create table if not exists last_update(date numeric);";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()
        ) {
            LOG.info("create table last_update ", statement.toString());
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            LOG.error("Error create table last_update", e);
            throw new VacancyDaoException("Error create table last_update", e);
        }
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void insert(Vacancy vacancy) throws VacancyDaoException {
        String sql = "insert into vacancy(title, link, author, date) values(?,?,?,?);";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, vacancy.getTitle());
            statement.setString(2, vacancy.getLink());
            statement.setString(3, vacancy.getAuthor());
            statement.setDate(4, new Date(vacancy.getDate().getTime()));
            LOG.info("insert vacancy ", statement.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Error insert vacancy", e);
            throw new VacancyDaoException("Error create table", e);
        }
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void insertSet(Set<Vacancy> vacancies) throws VacancyDaoException {
        String sql = "insert into vacancy(title, link, author, date) values(?, ?, ?, ?);";
        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            Iterator<Vacancy> iterator = vacancies.iterator();
            while (iterator.hasNext()) {
                Vacancy vacancy = iterator.next();
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
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    LOG.error("Error connection rollback", e);
                    throw new VacancyDaoException("Error connection rollback", e);
                }
            }
            throw new VacancyDaoException("Error insert set vacancies", sqle);
        } finally {
            try {
                connection.setAutoCommit(true);
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOG.error("Error set autocommit or close connection", e);
                throw new VacancyDaoException("Error set autocommit or close connection", e);
            }
        }
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void delete(Vacancy vacancy) throws VacancyDaoException {
        String sql = "delete from vacancy where link = ?;";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, vacancy.getLink());
            LOG.info("delete vacancy ", vacancy.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Error delete vacancy", e);
            throw new VacancyDaoException("Error delete vacancy", e);
        }
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public Vacancy getById(int id) throws VacancyDaoException {
        Vacancy vacancy = null;
        String sql = "select id, title, link, author, date from vacancy where id = ?;";
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
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
            throw new VacancyDaoException("Error getById vacancy", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LOG.error("Error result set close", e);
                    throw new VacancyDaoException("Error result set close", e);
                }
            }
        }
        return vacancy;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public List<Vacancy> getAll() throws VacancyDaoException {
        String sql = "select id, title, link, author, date from vacancy;";
        List<Vacancy> vacancies = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()
        ) {
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
            throw new VacancyDaoException("Error get all vacancies", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LOG.error("Error result set close", e);
                    throw new VacancyDaoException("Error result set close", e);
                }
            }
        }
        return vacancies;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void deleteAll() throws VacancyDaoException {
        String sql = "delete from vacancy;";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            LOG.error("Error delete all vacancies ", e);
            throw new VacancyDaoException("Error delete all vacancies ", e);
        }
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void insertLastUpdateDate(long date) throws VacancyDaoException {
        String sql = "insert into last_update (date) values (?);";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setDate(1, new Date(date));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Error insert last update date", e);
            throw new VacancyDaoException("Error insert last update date", e);
        }
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void updateLastUpdateDate(long date) throws VacancyDaoException {
        String sql = "update last_update set date = ?;";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setDate(1, new Date(date));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Error update last update date", e);
            throw new VacancyDaoException("Error update last update date", e);
        }
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public java.util.Date getLastUpdateDate() throws VacancyDaoException {
        java.util.Date result;
        String sql = "select date from last_update;";
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()
        ) {
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            result = resultSet.getDate("date");
        } catch (SQLException e) {
            LOG.error("Error get last update date", e);
            throw new VacancyDaoException("Error get last update date", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOG.error("Error close result set", e);
                throw new VacancyDaoException("Error close result set", e);
            }
        }
        return result;
    }
}
