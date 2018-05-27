package ru.job4j.sort;

/**
 * User
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class User implements Comparable<User> {

    /**
     * Имя
     */
    private String name;

    /**
     * Возраст
     */
    private int age;

    /**
     * Конструктор с параметрами
     * @param name
     * @param age
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public int compareTo(User o) {
        int result = Integer.compare(this.age, o.age);
        return result != 0 ? result : this.name.compareTo(o.name);
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("User: %s, %s", this.name, this.age);
    }

    /**
     * Method getAge
     * @return age
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Method getName
     * @return name
     */
    public String getName() {
        return this.name;
    }
}
