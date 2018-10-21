package ru.job4j.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.service.User;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * DBStore
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class DBStore implements Store<User> {

    /**
     * logger
     */
    private static final Logger LOG = LogManager.getLogger(DBStore.class.getName());

    /**
     * pool connections
     */
    private static final BasicDataSource SOURCE = new BasicDataSource();

    private static final DBStore INSTANCE = new DBStore();

    private DBStore() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }
        SOURCE.setUrl("jdbc:sqlite:dbstore.sqlite");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        createTables();
        insertData();
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    /**
     * Method createTables
     */
    private void createTables() {
        String createRoles = "create table if not exists roles("
                + "id integer primary key autoincrement not null, "
                + "role text unique not null);";
        String createUsers = "create table if not exists users("
                + "id integer primary key autoincrement not null, "
                + "login text unique not null, "
                + "name text , "
                + "email text , "
                + "password text not null, "
                + "role_id integer references roles(id) not null, "
                + "created numeric not null);";
        try (Connection connection = SOURCE.getConnection();
             Statement stRoles = connection.createStatement();
             Statement stUsers = connection.createStatement()
        ) {
            stRoles.executeUpdate(createRoles);
            LOG.info("create table roles if not exists");
            stUsers.executeUpdate(createUsers);
            LOG.info("create table users if not exists");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Method insertData
     */
    private void insertData() {
        String insertRoles = "insert or ignore into roles (role) values(?), (?);";
        String insertAdmin = "insert or ignore into users "
                + "(login, name, email, password, role_id, created) "
                + "values (?, ?, ?, ?, ?, ?);";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement stRoles = connection.prepareStatement(insertRoles);
             PreparedStatement stAdmin = connection.prepareStatement(insertAdmin)
        ) {
            stRoles.setString(1, "admin");
            stRoles.setString(2, "user");
            stRoles.executeUpdate();
            LOG.info("insert roles admin and user");
            stAdmin.setString(1, "admin");
            stAdmin.setString(2, "admin");
            stAdmin.setString(3, "admin@admin");
            stAdmin.setString(4, "admin");
            stAdmin.setInt(5, 1);
            stAdmin.setDate(6, new Date(new java.util.Date().getTime()));
            stAdmin.executeUpdate();
            LOG.info("insert admin into users");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Method deleteAll users
     */
    @Override
    public void deleteAll() {
        String sql = "delete from users;";
        try (Connection connection = SOURCE.getConnection();
            Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(sql);
            LOG.info("clear table users");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public int add(User user) {
        String sql = "insert into users (login, name, email, password, role_id, created) values(?, ?, ?, ?, ?, ?);";
        int result = -1;
        ResultSet resultSet = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getRole());
            statement.setDate(6, new Date(user.getCreateDate().getTime()));
            statement.executeUpdate();
            LOG.info("insert new user");
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            if (resultSet.getRow() > 0) {
                result = resultSet.getInt(1);
                LOG.info("new user id=" + result);
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
     * ${@inheritDoc}
     */
    @Override
    public void update(int id, User user) {
        String sql = "update users set login=?, name=?, email=?, password=?, role_id=? where id=?;";
        try (Connection connection = SOURCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getRole());
            statement.setInt(6, id);
            statement.executeUpdate();
            LOG.info("update user");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void delete(int id) {
        String sql = "delete from users where id=?;";
        try (Connection connection = SOURCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
            LOG.info("delete user");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public Collection<User> findAll() {
        Collection<User> users = new ArrayList<>();
        ResultSet resultSet = null;
        String sql = "select id, login, name, email, password, role_id, created from users;";
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement()
        ) {
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("login"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getInt("role_id"),
                    resultSet.getDate("created")
                );
                users.add(user);
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
        return users;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public User findById(int id) {
        String sql = "select id, login, name, email, password, role_id, created from users where id=?;";
        User user = null;
        ResultSet resultSet = null;
        try (Connection connection = SOURCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            if (resultSet.getRow() > 0) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("role_id"),
                        resultSet.getDate("created")
                );
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
        return user;
    }
}
