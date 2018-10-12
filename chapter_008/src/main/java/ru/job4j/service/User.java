package ru.job4j.service;

import java.util.Date;

/**
 * User
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class User {

    private static int count = 1;
    private final int id;
    private String login;
    private String name;
    private String email;
    private final Date createDate;

    public User(String login, String name, String email, Date createDate) {
        this.id = nextId();
        this.login = login;
        this.name = name;
        this.email = email;
        this.createDate = createDate;
    }

    private int nextId() {
        return count++;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", login='" + login + '\''
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + ", createDate=" + createDate
                + '}';
    }
}
