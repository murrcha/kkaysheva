package ru.job4j.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * ValidateStub
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ValidateStub implements Validate {

    private final Map<Integer, User> store = new HashMap<>();

    @Override
    public String add(User user) {
        store.put(user.getId(), user);
        return "User added";
    }

    @Override
    public String update(int id, User user) {
        store.replace(id, user);
        return "User updated";
    }

    @Override
    public String delete(int id) {
        store.remove(id);
        return "User deleted";
    }

    @Override
    public String deleteAll() {
        store.clear();
        return "All users deleted";
    }

    @Override
    public Collection<User> findAll() {
        return store.values();
    }

    @Override
    public User findById(int id) {
        return store.get(id);
    }

    @Override
    public int isSuccessAuth(String login, String password) {
        int result = -1;
        for (User user : store.values()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user.getId();
                break;
            }
        }
        return result;
    }
}
