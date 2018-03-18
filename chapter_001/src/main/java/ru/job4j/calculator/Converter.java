package ru.job4j.calculator;

/**
 * Converter.
 *
 * 1 dollar = 60 ruble
 * 1 euro = 70 ruble
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Converter {

    /**
     *  kurs euro and dollar to one ruble
     */
    private static final int KURS_EURO = 70;
    private static final int KURS_DOLLAR = 60;

    /**
     * Method rubleToEuro - convert ruble to euro
     * @param value ruble
     * @return value euro
     */
    public int rubleToEuro(int value) {
        return value / KURS_EURO;
    }

    /**
     * Method rubleToDollar - convert ruble to dollar
     * @param value ruble
     * @return value dollar
     */
    public int rubleToDollar(int value) {
        return value / KURS_DOLLAR;
    }

    /**
     * Method euroToRuble - convert euro to ruble
     * @param value euro
     * @return value ruble
     */
    public int euroToRuble(int value) {
        return value * KURS_EURO;
    }

    /**
     * Method dollarToRuble - convert dollar to ruble
     * @param value dollar
     * @return value ruble
     */
    public int dollarToRuble(int value) {
        return value * KURS_DOLLAR;
    }
}
