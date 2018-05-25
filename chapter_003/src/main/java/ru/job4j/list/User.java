package ru.job4j.list;

/**
 * User - пользователь
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class User {

    /**
     * Идентификатор
     */
    private int id;

    /**
     * Имя
     */
    private String name;

    /**
     * Город
     */
    private String city;

    /**
     * Constructor
     * @param id
     * @param name
     * @param city
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Method getId
     * @return
     */
    public int getId() {
        return this.id;
    }

    /**
     * Method getName
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method getCity
     * @return
     */
    public String getCity() {
        return this.city;
    }
}
