package ru.job4j.store;

import java.util.Collection;

/**
 * Store
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public interface Store<T> {

    /**
     * Method add user
     * @param user user
     */
    int add(T user);

    /**
     * Method update user
     * @param id user
     */
    void update(int id, T user);

    /**
     * Method delete user
     * @param id user
     */
    void delete(int id);

    /**
     * Method delete all users
     */
    void deleteAll();

    /**
     * Method findAll users
     * @return users
     */
    Collection<T> findAll();

    /**
     * Method findById user
     * @param id user
     * @return user
     */
    T findById(int id);
}
