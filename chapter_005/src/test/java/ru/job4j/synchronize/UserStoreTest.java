package ru.job4j.synchronize;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * UserStore Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class UserStoreTest {

    private class TestThread extends Thread {

        private final UserStore store;

        public TestThread(final UserStore store) {
            this.store = store;
        }

        /**
         * ${@inheritDoc}
         */
        @Override
        public void run() {
            this.store.add(new User(1, 100));
            this.store.update(new User(1, 200));
            this.store.add(new User(2, 100));
            this.store.update(new User(2, 200));
            this.store.transfer(1, 2, 200);
            this.store.transfer(2, 1, 400);
        }
    }

    private UserStore store;

    @Before
    public void beforeTest() {
        store = new UserStore();
    }

    /**
     * Test add
     */
    @Test
    public void whenAddNewUserThenUserAddedAndReturnTrue() {
        assertThat(store.add(new User(1, 200)), is(true));
        assertThat(store.add(new User(1, 100)), is(false));
    }

    /**
     * Test update
     */
    @Test
    public void whenUpdateExistUserThenUserUpdatedAndReturnTrue() {
        assertThat(store.add(new User(1, 200)), is(true));
        assertThat(store.update(new User(1, 100)), is(true));
        assertThat(store.update(new User(2, 300)), is(false));
    }

    /**
     * Test delete
     */
    @Test
    public void whenDeleteExistUserThenUserDeletedAndReturnTrue() {
        assertThat(store.add(new User(1, 200)), is(true));
        assertThat(store.delete(new User(1, 200)), is(true));
        assertThat(store.delete(new User(1, 200)), is(false));
        assertThat(store.delete(new User(2, 100)), is(false));
    }

    /**
     * Test transfer
     */
    @Test
    public void whenTransferFromUserToUserThenReturnTrue() {
        assertThat(store.add(new User(1, 100)), is(true));
        assertThat(store.add(new User(2, 100)), is(true));
        assertThat(store.transfer(1, 2, 100), is(true));
        assertThat(store.transfer(2, 1, 300), is(false));
        assertThat(store.transfer(2, 1, 200), is(true));
    }

    /**
     * Test synchronized
     */
    @Test
    public void whenStartTwoThreadsThenThreadsIsSynchronized() {
        Thread first = new TestThread(store);
        Thread second = new TestThread(store);
        first.start();
        second.start();
        try {
            first.join();
            second.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        assertThat(store.transfer(1, 2, 400), is(true));
        assertThat(store.transfer(1, 2, 400), is(false));
    }
}