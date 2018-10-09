package ru.job4j.store;


import ru.job4j.service.User;

import java.util.Collection;
import java.util.List;

/**
 * Store
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public interface Store {

    /**
     * Method add user
     * @param user user
     */
    void add(User user);

    /**
     * Method update user
     * @param id user
     */
    void update(int id, User user);

    /**
     * Method delete user
     * @param id user
     */
    void delete(int id);

    /**
     * Method findAll users
     * @return users
     */
    Collection<User> findAll();

    /**
     * Method findById user
     * @param id user
     * @return user
     */
    User findById(int id);
}
