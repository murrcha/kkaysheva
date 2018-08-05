package ru.job4j.pools;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ThreadPool
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ThreadPool {

    /**
     * List of threads for poll
     */
    private final List<PoolThread> threads = new LinkedList<>();

    /**
     * Queue of tasks for work
     */
    private final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();

    /**
     * Count of processors at system
     */
    private int size = Runtime.getRuntime().availableProcessors();

    /**
     * State of pool
     */
    private boolean isStopped = false;

    /**
     * Init threads in pool
     */
    public ThreadPool() {
        for (int index = 0; index < size; index++) {
            threads.add(new PoolThread(tasks));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    /**
     * Method work add job to queue of tasks
     * @param job for work
     */
    public void work(Runnable job) {
        if (this.isStopped) {
            throw new IllegalStateException("ThreadPool is stopped");
        }
        try {
            this.tasks.put(job);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

    }

    /**
     * Method shutdown interrupt all threads in pool and change state of pool
     */
    public void shutdown() {
        this.isStopped = true;
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    /**
     * Method isStopped return isStopped
     * @return isStopped
     */
    public boolean isStopped() {
        return isStopped;
    }
}
