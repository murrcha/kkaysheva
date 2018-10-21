package ru.job4j.service;

import ru.job4j.store.DBStore;
import ru.job4j.store.Store;

import java.util.Collection;

/**
 * ValidateService
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ValidateService implements Validate {

    private static final ValidateService INSTANCE = new ValidateService();

    /**
     * store
     */
    private final Store<User> store = DBStore.getInstance();;

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
     * Method isSuccessAuth
     * @param login user
     * @param password user
     * @return id user if exists or -1 if not existså
     */
    public int isSuccessAuth(String login, String password) {
        int result = -1;
        for (User user : store.findAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user.getId();
                break;
            }
        }
        return result;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public String add(User user) {
        String result = "Error add user";
        int id = store.add(user);
        if (id > 0) {
            result = "User added";
        }
        return result;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public String update(int id, User user) {
        checkId(id);
        String result = "Error update user";
        if (isUserExists(id)) {
            store.update(id, user);
            result = "User updated";
        }
        return result;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public String delete(int id) {
        checkId(id);
        String result = "Error delete user";
        if (isUserExists(id)) {
            store.delete(id);
            result = "User deleted";
        }
        return result;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public String deleteAll() {
        store.deleteAll();
        return "Delete all users";
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public Collection<User> findAll() {
        return store.findAll();
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public User findById(int id) {
        checkId(id);
        return isUserExists(id) ? store.findById(id) : null;
    }
}
