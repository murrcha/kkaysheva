package ru.job4j.sort;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * SortUser
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SortUser {

    /**
     * Method sort - сортирует список пользователей по возрасту в порядке возрастания,
     * в рамках одного возраста сортирует по имени
     * @param list список пользователей
     * @return отсортированная структура пользователей
     */
    public Set<User> sort(List<User> list) {
        Set<User> result = new TreeSet<>();
        result.addAll(list);
        return result;
    }

    /**
     * Method sortNameLength - сортирует список пользователей по длине имени в порядке возрастания
     * @param list список пользователей
     * @return отсортированный список пользователей
     */
    public List<User> sortNameLength(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getName().length(), o2.getName().length());
            }
        });
        return list;
    }

    /**
     * Method sortByAllFields - сортирует список пользователей сначала по имени,
     * потом по возрасту в порядке возрастания
     * @param list список пользователей
     * @return отсортированный список пользователей
     */
    public List<User> sortByAllFields(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = o1.getName().compareTo(o2.getName());
                return result != 0 ? result : Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        return list;
    }
}
