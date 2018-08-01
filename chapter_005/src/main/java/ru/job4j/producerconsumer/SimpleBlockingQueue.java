package ru.job4j.producerconsumer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;

/**
 * SimpleBlockingQueue
 * @param <T>
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    private final static int LIMIT = 3;
    private static final Logger LOG = LogManager.getLogger(SimpleBlockingQueue.class.getName());

    @GuardedBy("this")
    private final Queue<T> queue;

    public SimpleBlockingQueue() {
        queue = new LinkedList<>();
    }

    /**
     * Method offer - add value to queue
     * @param value
     */
    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == LIMIT) {
            wait();
        }
        if (queue.size() < LIMIT) {
            notify();
        }
        queue.add(value);
        LOG.info(String.format("offer %s ok", value));
    }

    /**
     * Method poll - get and remove value from queue
     * @return value
     */
    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        if (queue.size() > 0) {
            notify();
        }
        T value = queue.remove();
        LOG.info(String.format("poll %s ok", value));
        return value;
    }

    /**
     * Method isEmpty
     * @return isEmpty
     */
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
