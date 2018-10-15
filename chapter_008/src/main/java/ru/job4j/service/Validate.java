package ru.job4j.service;

import java.util.Collection;

/**
 * Validate
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public interface Validate {

    /**
     * Method add
     * @param user object
     */
    String add(User user);

    /**
     * Method update
     * @param id user
     * @param user object
     */
    String update(int id, User user);

    /**
     * Method delete
     * @param id user
     */
    String delete(int id);

    /**
     * Method deleteALl
     */
    String deleteAll();

    /**
     * Method findAll
     * @return users
     */
    Collection<User> findAll();

    /**
     * Method findById
     * @param id user
     * @return user
     */
    User findById(int id);
}
