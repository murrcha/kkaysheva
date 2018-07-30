package ru.job4j.producerconsumer;

/**
 * Producer
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Producer extends Thread {

    private final SimpleBlockingQueue<Integer> queue;

    public Producer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void run() {
        int value = 1;
        int count = 0;
        while (count < 10) {
            count++;
            try {
                queue.offer(value++);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
