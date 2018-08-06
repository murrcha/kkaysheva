package ru.job4j.executorservice;

/**
 * User
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class User {

    /**
     * Name
     */
    private String name;

    /**
     * Email
     */
    private String email;

    /**
     * Init name and email
     * @param name
     * @param email
     */
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Method getName - return name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method getEmail - return email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
