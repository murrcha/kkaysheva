package ru.job4j.magnit;

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
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
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
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                List<Field> fields = new ArrayList<>();
                fields.add(new Field(resultSet.getInt("field")));
                result.add(new Entry(fields));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method connect - connect to db
     */
    private void connect() {
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
