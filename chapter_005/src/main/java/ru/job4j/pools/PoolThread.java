package ru.job4j.pools;

import java.util.concurrent.BlockingQueue;

/**
 * PoolThread - thread for ThreadPool
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class PoolThread extends Thread {

    /**
     * Queue of tasks for work
     */
    private BlockingQueue<Runnable> tasks;

    /**
     * Init tasks
     * @param tasks
     */
    public PoolThread(BlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Runnable task = tasks.take();
                task.run();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
