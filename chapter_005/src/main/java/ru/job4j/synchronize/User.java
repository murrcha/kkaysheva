package ru.job4j.synchronize;

/**
 * User
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class User {

    private final int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * Method getId
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Method getAmount
     * @return amount
     */
    public int getAmount() {
        return this.amount;
    }
}
