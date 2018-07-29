package ru.job4j.jmm;

/**
 * VisibilityOfSharedObjects - demo class for jmm task
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class VisibilityOfSharedObjects {
    public static void main(String[] args) {
        Counter counter = new Counter();
        int limit = 3;
        Thread threadOne = new Thread(new WriteThread(counter, limit));
        threadOne.setName("Thread 0 write");
        Thread threadTwo = new Thread(new ReadThread(counter,  limit));
        threadTwo.setName("Thread 1 read");
        Thread threadThree = new Thread(new ReadThread(counter, limit));
        threadThree.setName("Thread 2 read");
        Thread threadFour = new Thread(new ReadThread(counter, limit));
        threadFour.setName("Thread 3 read");
        threadOne.start();
        threadTwo.start();
        threadThree.start();
        threadFour.start();
    }
}
