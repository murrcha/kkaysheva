package ru.job4j.store;

import ru.job4j.service.User;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * MemoryStore
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class MemoryStore implements Store<User> {

    private static final MemoryStore INSTANCE = new MemoryStore();

    /**
     * users container
     */
    private final ConcurrentMap<Integer, User> users = new ConcurrentHashMap<>();

    private MemoryStore() {

    }

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    public int add(User user) {
        users.put(user.getId(), user);
        return user.getId();
    }

    @Override
    public void update(int id, User user) {
        users.put(id, user);
    }

    @Override
    public void delete(int id) {
        users.remove(id);
    }

    @Override
    public void deleteAll() {
        users.clear();
    }

    @Override
    public Collection<User> findAll() {
        return users.values();
    }

    @Override
    public User findById(int id) {
        return users.get(id);
    }
}
