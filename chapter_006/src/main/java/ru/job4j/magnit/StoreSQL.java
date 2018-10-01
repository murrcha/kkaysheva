package ru.job4j.magnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.magnit.pojo.Entry;
import ru.job4j.magnit.pojo.Field;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 * StoreSQL
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
@SuppressWarnings("SyntaxError")
public class StoreSQL implements AutoCloseable {

    /**
     * logger
     */
    private static final Logger LOG = LogManager.getLogger(StoreSQL.class.getName());

    /**
     * connection string
     */
    private static final String URL = "jdbc:sqlite:magnit.db";

    /**
     * connection to db
     */
    private Connection connection;

    /**
     * init store
     */
    public StoreSQL() {
        connect();
        createTable();
        clearTable();
    }

    /**
     * Method generate - generate n records in entity table
     * @param n count records
     */
    public void generate(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(String.format("Illegal argument n = %s", n));
        }
        String sql = "INSERT INTO entry(field) VALUES(?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            for (int index = 1; index <= n; index++) {
                statement.setInt(1, index);
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    LOG.error(sqle.getMessage(), sqle);
                }
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Method getEntries
     * @return list entries
     */
    public List<Entry> getEntries() {
        List<Entry> result = new ArrayList<>();
        String sql = "SELECT field from entry";
        ResultSet resultSet = null;
        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                List<Field> fields = new ArrayList<>();
                fields.add(new Field(resultSet.getInt("field")));
                result.add(new Entry(fields));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Method connect - connect to db
     */
    private void connect() {
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Method createTable - create table entry
     */
    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS entry(field integer);";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Method clearTable - delete all rows from table entry
     */
    private void clearTable() {
        String sql = "DELETE FROM entry;";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
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
