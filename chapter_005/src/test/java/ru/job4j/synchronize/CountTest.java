package ru.job4j.synchronize;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Count Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class CountTest {

    private class ThreadCount extends Thread {
        private final Count count;

        public ThreadCount(Count count) {
            this.count = count;
        }

        /**
         * ${@inheritDoc}
         */
        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenExecute2ThreadsThen2() {
        final Count count = new Count();
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        first.start();
        second.start();
        try {
            first.join();
            second.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        assertThat(count.get(), is(2));
    }
}