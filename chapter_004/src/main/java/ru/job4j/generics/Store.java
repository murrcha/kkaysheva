package ru.job4j.generics;

/**
 * Store
 * @param <T>
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public interface Store<T extends Base> {

    /**
     * Method add - add new element
     * @param model - element
     */
    void add(T model);

    /**
     * Method replace - replace element
     * @param id - id element
     * @param model - element
     * @return result (true or false)
     */
    boolean replace(String id, T model);

    /**
     * Method delete - delete element
     * @param id - id element
     * @return result (true or false)
     */
    boolean delete(String id);

    /**
     * Method findById - find element by id
     * @param id - id element
     * @return - result (element or null)
     */
    T findById(String id);
}
