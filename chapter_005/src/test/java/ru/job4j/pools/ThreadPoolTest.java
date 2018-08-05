package ru.job4j.pools;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * ThreadPool Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ThreadPoolTest {

    private class GoodJob implements Runnable {
        /**
         * ${@inheritDoc}
         */
        @Override
        public void run() {
            System.out.println("good job");
        }
    }

    private class BadJob implements Runnable {
        /**
         * ${@inheritDoc}
         */
        @Override
        public void run() {
            System.out.println("bad job");
        }
    }

    /**
     * Test work, shutdown
     */
    @Test
    public void whenThen() {
        ThreadPool pool = new ThreadPool();
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            pool.work(new GoodJob());
            pool.work(new BadJob());
        }

        pool.shutdown();
        assertThat(pool.isStopped(), is(true));
    }
}