package ru.job4j.search;

import org.omg.CORBA.PERSIST_STORE;

import java.util.ArrayList;
import java.util.List;

/**
 * PhoneDictionary - телефонный справочник
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class PhoneDictionary {

    /**
     * Список пользователей
     */
    private List<Person> persons = new ArrayList<>();

    /**
     * Method add - добавляет в список пользователя
     * @param person
     */
    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Method find - ищет пользователей соответствующих ключу
     * @param key ключ
     * @return список пользователей
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (Person person : this.persons) {
            if (person.getName().contains(key) || person.getSurname().contains(key)
                    || person.getPhone().contains(key) || person.getAddress().contains(key)) {
                result.add(person);
            }
        }
        return result;
    }
}
