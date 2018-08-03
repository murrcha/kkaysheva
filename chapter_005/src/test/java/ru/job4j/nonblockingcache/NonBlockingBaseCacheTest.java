package ru.job4j.nonblockingcache;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * NonBlockingBaseCache Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class NonBlockingBaseCacheTest {

    private NonBlockingBaseCache cache = new NonBlockingBaseCache();

    class TestThread extends Thread {

        private final NonBlockingBaseCache cache;

        public TestThread(NonBlockingBaseCache cache) {
            this.cache = cache;
        }

        @Override
        public void run() {
            int count = 0;
            while (count < 5) {
                count++;
                Base model = cache.get(1);
                model.setName("Ivan");
                cache.update(model);
                model = cache.get(2);
                model.setName("Oleg");
                cache.update(model);
            }
        }
    }

    class ModTestValueThread extends Thread {

        private final NonBlockingBaseCache cache;

        public ModTestValueThread(NonBlockingBaseCache cache) {
            this.cache = cache;
        }

        @Override
        public void run() {
            int count = 0;
            while (count < 999999) {
                count++;
                int temp = cache.getTestValue();
                temp++;
                int version = cache.getModCount();
                cache.setTestValue(temp, version);
            }
        }
    }

    @Before
    public void beforeTest() {
        cache.add(new Base(1, "Artem"));
        cache.add(new Base(2, "Kiril"));
    }

    /**
     * Test add
     */
    @Test
    public void whenAddNewBaseModelsThenCacheContainsNewBaseModels() {
        assertThat(cache.get(1).getVersion(), is(0));
        assertThat(cache.get(2).getVersion(), is(0));
        assertThat(cache.get(1).getName(), is("Artem"));
        assertThat(cache.get(2).getName(), is("Kiril"));
        assertThat(cache.get(1).getId(), is(1));
        assertThat(cache.get(2).getId(), is(2));
    }

    /**
     * Test delete
     */
    @Test
    public void whenDeleteExistBaseModelThenBaseModelDeletedFromCache() {
        Base model = cache.get(1);
        cache.delete(model);
        model = cache.get(2);
        cache.delete(model);
        assertThat(cache.get(1), nullValue());
        assertThat(cache.get(2), nullValue());
    }

    /**
     * Test update
     */
    @Test
    public void whenUpdateExistBaseModelThenBaseModelUpdated() {
        Base model = cache.get(1);
        model.setName("Jack");
        cache.update(model);
        assertThat(cache.get(1).getVersion(), is(1));
        model = cache.get(2);
        model.setName("Tom");
        cache.update(model);
        assertThat(cache.get(2).getVersion(), is(1));
        model = cache.get(2);
        model.setName("Test");
        cache.update(model);
        assertThat(cache.get(2).getVersion(), is(2));
    }

    /**
     * Test update in threads
     */
    @Test
    public void whenRun2ThreadsForUpdateOneObjectInCacheThenVersionChanged() {
        TestThread threadOne = new TestThread(cache);
        TestThread threadTwo = new TestThread(cache);
        threadOne.start();
        threadTwo.start();
        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(cache.get(1).getName(), is("Ivan"));
        assertThat(cache.get(2).getName(), is("Oleg"));
        assertThat(cache.get(1).getVersion(), is(10));
        assertThat(cache.get(2).getVersion(), is(10));
    }

    /**
     * Test OptimisticException
     */
    @Test
    public void when2ThreadsWriteOneValueThenReturnException() {
        ModTestValueThread thread1 = new ModTestValueThread(cache);
        ModTestValueThread thread2 = new ModTestValueThread(cache);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}