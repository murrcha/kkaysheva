package ru.job4j.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Store
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Store {

    /**
     * Method diff - return statistics between previous and current lists
     * @param previous
     * @param current
     * @return
     */
    public Info diff(List<User> previous, List<User> current) {
        int addedCount = 0;
        int changedCount = 0;
        int deletedCount = 0;
        HashMap<Integer, String> currentUsers = new HashMap<>(current.size());
        for (User user : current) {
            currentUsers.put(user.id, user.name);
        }
        for (User user : previous) {
            if (!currentUsers.containsKey(user.id)) {
                deletedCount++;
            } else if (!user.name.equals(currentUsers.get(user.id))) {
                changedCount++;
            }
        }
        int diff = current.size() - (previous.size() - deletedCount);
        if (diff > 0) {
            addedCount = diff;
        }
        return new Info(addedCount, changedCount, deletedCount);
    }

    static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof User)) {
                return false;
            }
            User u = (User) obj;
            return u.name.equals(name) && u.id == id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }
}
