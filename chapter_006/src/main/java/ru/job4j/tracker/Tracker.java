package ru.job4j.tracker;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Tracker - хранилище для заявок
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Tracker implements AutoCloseable {

    /**
     * default config file
     */
    private static final String CONFIG = "config.properties";

    /**
     * not exists flag
     */
    private static final String NOT_EXISTS = "f";

    /**
     * connection object
     */
    private Connection connection;

    /**
     * properties object
     */
    private Properties properties;

    /**
     * init ru.job4j.ru.job4j.tracker by default
     */
    public Tracker() {
        initProperty(CONFIG);
        checkExistsDB();
        if (connection == null) {
            initConnection();
        }
    }

    /**
     * init ru.job4j.ru.job4j.tracker by config file
     * @param config file
     */
    public Tracker(String config) {
        initProperty(config);
        checkExistsDB();
        if (connection == null) {
            initConnection();
        }
    }

    /**
     * Method initProperty - init property file
     * @param config file
     */
    private void initProperty(String config) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(config)) {
            properties = new Properties();
            properties.load(is);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Method initConnection - init connection to database ru.job4j.ru.job4j.tracker
     */
    private void initConnection() {
        try {
            String host = properties.getProperty("db.host");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            connection = DriverManager.getConnection(host, user, password);
        } catch (SQLException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Method checkExistsDB - check database ru.job4j.ru.job4j.tracker exists or not
     * if not, then create database
     * and then connect to database ru.job4j.ru.job4j.tracker
     * and create table items
     */
    private void checkExistsDB() {
        try (Connection defaultConnection = DriverManager.getConnection(
                                     properties.getProperty("db.default_host"),
                                     properties.getProperty("db.user"),
                                     properties.getProperty("db.password"));
        Statement stCheckExistsDB = defaultConnection.createStatement()
        ) {
            ResultSet resultSet = stCheckExistsDB.executeQuery(properties.getProperty("sql.database_exists"));
            resultSet.next();
            if (resultSet.getString("exists").equals(NOT_EXISTS)) {
                stCheckExistsDB.executeUpdate(properties.getProperty("sql.create_database"));
                resultSet.close();
                initConnection();
                Statement stCreateTable = connection.createStatement();
                stCreateTable.executeUpdate(properties.getProperty("sql.create_table"));
                stCreateTable.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * Method add - добавляет новую заявку
     * @param item - заявка
     * @return новую заявку
     */
    public Item add(Item item) {
        try (PreparedStatement statement =
                     connection.prepareStatement(properties.getProperty("sql.add_new_item"))
        ) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            Calendar calendar = Calendar.getInstance();
            java.util.Date now = calendar.getTime();
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
            statement.setTimestamp(3, currentTimestamp);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("id");
            item.setId(id);
            resultSet.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return item;
    }

    /**
     * Method replace - заменяет заявку
     * @param id - идентификатор заявки
     * @param item - заявка
     */
    public void replace(int id, Item item) {
        try (PreparedStatement statement =
                     connection.prepareStatement(properties.getProperty("sql.update_item"))
        ) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * Method delete - удаляет заявку
     * @param id - ид заявки
     */
    public void delete(int id) {
        try (PreparedStatement statement =
                     connection.prepareStatement(properties.getProperty("sql.delete_item"))
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * Method findAll - ищет все заявки
     * @return - массив всех заявок
     */
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(properties.getProperty("sql.get_all_items"))
        ) {
            if (resultSet != null) {
                while (resultSet.next()) {
                    Item item = new Item();
                    item.setId(resultSet.getInt("id"));
                    item.setName(resultSet.getString("name"));
                    item.setDescription(resultSet.getString("description"));
                    item.setCreated(resultSet.getTimestamp("create_date"));
                    items.add(item);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return items;
    }

    /**
     * Method findByName - ищет заявки по имени
     * @param key - ключ для поиска
     * @return - массив соответствующих ключу заявок
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement statement =
                connection.prepareStatement(properties.getProperty("sql.get_items_by_name"))
        ) {
            statement.setString(1, key);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setDescription(resultSet.getString("description"));
                item.setCreated(resultSet.getTimestamp("create_date"));
                result.add(item);
            }
            resultSet.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    /**
     * Method findById - ищет заявку по идентификатору
     * @param id - идентификатор
     * @return - заявка
     */
    public Item findById(int id) {
        Item result = null;
        try (PreparedStatement statement =
                     connection.prepareStatement(properties.getProperty("sql.get_item_by_id"))
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            if (resultSet.getRow() > 0) {
                result = new Item();
                result.setId(resultSet.getInt("id"));
                result.setName(resultSet.getString("name"));
                result.setDescription(resultSet.getString("description"));
                result.setCreated(resultSet.getTimestamp("create_date"));
            }
            resultSet.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
