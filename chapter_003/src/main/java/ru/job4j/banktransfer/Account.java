package ru.job4j.banktransfer;

/**
 * Account - счет
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Account {

    /**
     * Количество денег на счете
     */
    private double value;

    /**
     * Реквизиты счета
     */
    private String requisites;

    /**
     * Конструктор
     * @param requisites
     * @param value
     */
    public Account(String requisites, double value) {
        this.requisites = requisites;
        this.value = value;
    }

    /**
     * Method getValue
     * @return
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Method getRequisites
     * @return
     */
    public String getRequisites() {
        return this.requisites;
    }
}
