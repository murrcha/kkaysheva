package ru.job4j.jmm;

/**
 * ReadThread - demo class for jmm task
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ReadThread implements Runnable {

    private Counter counter;
    private int limit;

    public ReadThread(Counter counter, int limit) {
        this.counter = counter;
        this.limit = limit;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void run() {
        for (int index = 1; index <= limit; index++) {
            System.out.println(String.format("%s : %s", Thread.currentThread().getName(), counter.getCount()));
        }
    }
}
