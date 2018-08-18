package ru.job4j.producerconsumer;

/**
 * ParallelSearchStopConsumer
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ParallelSearchStopConsumer {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        final Thread consumer = new Thread(
                () -> {
                    while (true) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                            return;
                        }
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != 50; index++) {
                        try {
                            queue.offer(index);
                            Thread.sleep(500);
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                    }
                    consumer.interrupt();
                }
        ).start();
    }
}
