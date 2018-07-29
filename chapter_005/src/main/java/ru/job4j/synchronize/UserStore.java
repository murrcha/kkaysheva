package ru.job4j.synchronize;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStore {

    @GuardedBy("this")
    private final Map<Integer, Integer> users;

    public UserStore() {
        users = new HashMap<>();
    }

    /**
     * Method add - add new user
     * @param user
     * @return
     */
    public synchronized boolean add(User user) {
        boolean result = false;
        if (!users.containsKey(user.getId())) {
            users.put(user.getId(), user.getAmount());
            result = true;
        }
        return result;
    }

    /**
     * Method update - update exist user
     * @param user
     * @return
     */
    public synchronized boolean update(User user) {
        boolean result = false;
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user.getAmount());
            result = true;
        }
        return result;
    }

    /**
     * Method delete - delete exist user
     * @param user
     * @return
     */
    public synchronized boolean delete(User user) {
        boolean result = false;
        if (users.containsKey(user.getId())) {
            users.remove(user.getId());
            result = true;
        }
        return result;
    }

    /**
     * Method transfer - move money between users
     * @param fromId
     * @param toId
     * @param amount
     * @return
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        if (users.containsKey(fromId) && users.containsKey(toId)
                && users.get(fromId) >= amount) {
            users.put(fromId, users.get(fromId) - amount);
            users.put(toId, users.get(toId) + amount);
            result = true;
        }
        return result;
    }
}
