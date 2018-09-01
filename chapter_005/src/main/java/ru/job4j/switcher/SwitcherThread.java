package ru.job4j.switcher;

/**
 * SwitcherThread
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SwitcherThread extends Thread {

    private Switcher switcher;
    private int digit;

    public SwitcherThread(Switcher switcher, int digit) {
        this.switcher = switcher;
        this.digit = digit;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                switcher.addDigit(digit);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
