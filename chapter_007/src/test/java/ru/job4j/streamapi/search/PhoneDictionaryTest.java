package ru.job4j.streamapi.search;

import org.junit.Test;
import ru.job4j.search.Person;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * PhoneDictionary Test (stream api)
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version 0.1
 * @since 03.11.2018
 */
public class PhoneDictionaryTest {

    /**
     * Test find by name
     */
    @Test
    public void whenFindPersonByNameThenReturnPersonList() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Ксения", "Владимирова", "79992223435", "Ижевск"));
        phones.add(new Person("Ксения", "Иванова", "79993332324", "Омск"));
        List<Person> persons = phones.find("Ксен");
        assertThat(persons.get(0).getSurname(), is("Владимирова"));
        assertThat(persons.get(1).getSurname(), is("Иванова"));
    }

    /**
     * Test find by surname
     */
    @Test
    public void whenFindPersonBySurnameThenReturnPersonList() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Ксения", "Владимирова", "79992223435", "Ижевск"));
        phones.add(new Person("Ксения", "Иванова", "79993332324", "Омск"));
        List<Person> persons = phones.find("Иван");
        assertThat(persons.iterator().next().getAddress(), is("Омск"));
    }

    /**
     * Test find by phone
     */
    @Test
    public void whenFindPersonByPhoneThenReturnPersonList() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Ксения", "Владимирова", "79992223435", "Ижевск"));
        phones.add(new Person("Ксения", "Иванова", "79993332324", "Омск"));
        List<Person> persons = phones.find("799922234");
        assertThat(persons.iterator().next().getAddress(), is("Ижевск"));
    }

    /**
     * Test find by address
     */
    @Test
    public void whenFindPersonByAddressThenReturnPersonList() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Ксения", "Владимирова", "79992223435", "Ижевск"));
        phones.add(new Person("Ксения", "Иванова", "79993332324", "Омск"));
        List<Person> persons = phones.find("Ом");
        assertThat(persons.iterator().next().getSurname(), is("Иванова"));
    }

    /**
     * Test find by not found key
     */
    @Test
    public void whenFindPersonByNameThenReturnEmptyList() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Ксения", "Владимирова", "79992223435", "Ижевск"));
        phones.add(new Person("Ксения", "Иванова", "79993332324", "Омск"));
        List<Person> persons = phones.find("Анна");
        assertThat(persons.isEmpty(), is(true));
    }

}