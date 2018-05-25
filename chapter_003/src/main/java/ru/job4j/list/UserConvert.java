package ru.job4j.list;

import java.util.HashMap;
import java.util.List;

/**
 * UserConvert - конвертер
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class UserConvert {

    /**
     * Method process - преобразует список пользователей в map с ключом id
     * @param list - список пользователей
     * @return map
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User user : list) {
            result.put(user.getId(), user);
        }
        return result;
    }
}
