package ru.job4j.jmm;

/**
 * RaceConditions - demo class for jmm task
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class RaceConditions {
    public static void main(String[] args) {
        Counter counter = new Counter();
        int limit = 2;
        Thread threadOne = new Thread(new WriteThread(counter, limit));
        threadOne.setName("Thread 1 write");
        Thread threadTwo = new Thread(new WriteThread(counter, limit));
        threadTwo.setName("Thread 2 write");
        Thread threadThree = new Thread(new WriteThread(counter, limit));
        threadThree.setName("Thread 3 write");
        Thread threadFour = new Thread(new WriteThread(counter, limit));
        threadFour.setName("Thread 4 write");
        threadOne.start();
        threadTwo.start();
        threadThree.start();
        threadFour.start();
    }
}
