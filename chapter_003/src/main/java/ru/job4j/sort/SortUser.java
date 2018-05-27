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
}
