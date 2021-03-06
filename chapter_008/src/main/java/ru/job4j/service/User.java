package ru.job4j.service;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class User {

    private static final AtomicInteger COUNT = new AtomicInteger(1);
    private final int id;
    private String login;
    private String name;
    private String email;
    private String password;
    private int role;
    private final Date createDate;

    public User(String login, String name, String email, String password, int role, Date createDate) {
        this.id = nextId();
        this.login = login;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createDate = createDate;
    }

    public User(int id, String login, String name, String email, String password, int role,  Date createDate) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createDate = createDate;
    }

    private int nextId() {
        return COUNT.getAndIncrement();
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

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
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
