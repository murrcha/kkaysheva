package ru.job4j.deadlock;

import java.util.concurrent.CountDownLatch;

/**
 * DeadLock - guaranteed deadlock with CountDownLatch
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class DeadLock extends Thread {

    /**
     * Synchronizer with counter
     */
    private final CountDownLatch latch;

    /**
     * Objects for lock
     */
    private final Object object1;
    private final Object object2;

    public DeadLock(CountDownLatch latch, Object object1, Object object2) {
        this.latch = latch;
        this.object1 = object1;
        this.object2 = object2;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void run() {
        synchronized (object1) {
            latch.countDown();
            try {
                latch.await();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            synchronized (object2) {
                System.out.println("Finish");
            }
        }
    }

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);
        final Object object1 = new Object();
        final Object object2 = new Object();
        new DeadLock(latch, object1, object2).start();
        new DeadLock(latch, object2, object1).start();
    }
}
