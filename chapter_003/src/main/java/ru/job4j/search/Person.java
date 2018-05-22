package ru.job4j.search;

/**
 * Person - пользователь
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Person {

    /**
     * Имя и фамилия
     */
    private String name;
    private String surname;

    /**
     * Номер телефона
     */
    private String phone;

    /**
     * Адрес
     */
    private String address;

    /**
     * Конструктор - инициализация полей
     * @param name
     * @param surname
     * @param phone
     * @param address
     */
    public Person(String name, String surname, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    /**
     * Method getName
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method getSurname
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Method getPhone
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Method getAddress
     * @return address
     */
    public String getAddress() {
        return address;
    }
}
