package ru.job4j.streamapi.search;

import ru.job4j.search.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PhoneDictionary - телефонный справочник (stream api)
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version 0.1
 * @since 03.11.2018
 */
public class PhoneDictionary {

    /**
     * Список пользователей
     */
    private List<Person> persons = new ArrayList<>();

    /**
     * Method add - добавляет в список пользователя
     * @param person object
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
        return this.persons.stream()
                .filter(person -> person.getName().contains(key)
                || person.getSurname().contains(key)
                || person.getPhone().contains(key)
                || person.getAddress().contains(key))
                .collect(Collectors.toList());
    }
}
