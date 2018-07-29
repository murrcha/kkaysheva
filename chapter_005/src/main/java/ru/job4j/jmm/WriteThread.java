package ru.job4j.jmm;

/**
 * WriteTread - demo class for jmm task
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class WriteThread implements Runnable {

    private Counter counter;
    private int limit;

    public WriteThread(Counter counter, int limit) {
        this.counter = counter;
        this.limit = limit;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void run() {
        for (int index = 1; index <= limit; index++) {
            counter.setCount(counter.getCount() + 1);
            System.out.println(String.format("%s : %s", Thread.currentThread().getName(), counter.getCount()));
        }
    }
}
