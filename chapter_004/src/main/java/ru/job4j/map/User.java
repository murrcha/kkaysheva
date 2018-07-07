package ru.job4j.map;

import java.util.Calendar;

/**
 * User
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class User {

    private String name;

    private int children;

    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * Method getName
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method getChildren
     * @return
     */
    public int getChildren() {
        return this.children;
    }

    /**
     * Method getBirthday
     * @return
     */
    public Calendar getBirthday() {
        return birthday;
    }
}
