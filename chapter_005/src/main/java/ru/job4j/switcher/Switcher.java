package ru.job4j.switcher;

/**
 * Switcher
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Switcher {

    /**
     * constants for switch threads
     */
    private static final int LIMIT = 10;
    private static final int FIRST_DIGIT = 1;
    private static final int SECOND_DIGIT = 2;

    /**
     * string
     */
    private final StringBuilder string = new StringBuilder();
    private int counter = 0;
    private int currentDigit = FIRST_DIGIT;

    /**
     * Method addDigit
     * @param digit digit
     */
    public synchronized void addDigit(int digit) throws InterruptedException {
            if (digit != currentDigit) {
                wait();
            } else {
                string.append(String.valueOf(digit));
                counter++;
            }
            if (counter == LIMIT) {
                counter = 0;
                if (currentDigit == FIRST_DIGIT) {
                    currentDigit = SECOND_DIGIT;
                } else {
                    currentDigit = FIRST_DIGIT;
                }
                notify();
            }
    }

    /**
     * Method getString
     * @return string
     */
    public synchronized String getString() {
        return string.toString();
    }
}
