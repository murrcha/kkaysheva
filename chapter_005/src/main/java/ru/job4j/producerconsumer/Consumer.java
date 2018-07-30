package ru.job4j.producerconsumer;

/**
 * Consumer
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Consumer extends Thread {

    private final SimpleBlockingQueue<Integer> queue;

    public Consumer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void run() {
        int count = 0;
        while (count < 10) {
            count++;
            try {
                queue.poll();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
