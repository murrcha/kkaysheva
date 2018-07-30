package ru.job4j.producerconsumer;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * SimpleBlockingQueue Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleBlockingQueueTest {

    private SimpleBlockingQueue<Integer> queue;

    @Before
    public void beforeTest() {
        queue = new SimpleBlockingQueue<>();
    }

    /**
     * Test Producer and Consumer
     */
    @Test
    public void whenRunProducerAndConsumerAsThreadsThenAnyThreadBlockSharedQueue() {

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        producer.start();
        consumer.start();
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        assertThat(queue.isEmpty(), is(true));
    }
}