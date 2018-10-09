package ru.job4j.service;

import ru.job4j.store.MemoryStore;
import ru.job4j.store.Store;

import java.util.Collection;

/**
 * ValidateService
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ValidateService {

    private static final ValidateService INSTANCE = new ValidateService();

    /**
     * store
     */
    private final Store store = MemoryStore.getInstance();

    private ValidateService() {

    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    /**
     * Method checkId
     * @param id user
     */
    private void checkId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid id argument");
        }
    }

    /**
     * Method isUserExists
     * @param id user
     * @return result
     */
    private boolean isUserExists(int id) {
        return store.findById(id) != null;
    }

    /**
     * Method add
     * @param user object
     */
    public void add(User user) {
        if (!isUserExists(user.getId())) {
            store.add(user);
        }
    }

    /**
     * Method update
     * @param id user
     * @param user object
     */
    public void update(int id, User user) {
        checkId(id);
        if (isUserExists(id)) {
            store.update(id, user);
        }
    }

    /**
     * Method delete
     * @param id user
     */
    public void delete(int id) {
        checkId(id);
        if (isUserExists(id)) {
            store.delete(id);
        }
    }

    /**
     * Method findAll
     * @return users
     */
    public Collection<User> findAll() {
        return store.findAll();
    }

    /**
     * Method findById
     * @param id user
     * @return user
     */
    public User findById(int id) {
        checkId(id);
        return isUserExists(id) ? store.findById(id) : null;
    }
}
