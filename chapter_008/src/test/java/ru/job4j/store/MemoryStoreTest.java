package ru.job4j.store;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.service.User;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * MemoryStore Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class MemoryStoreTest {

    private Store store;

    @Before
    public void before() {
        store = MemoryStore.getInstance();
    }

    /**
     * Test add
     */
    @Test
    public void whenAddUserThenUserInMemory() {
        User user = new User("alex", "Alexander", "alex@mail.com", new Date());
        store.add(user);
        assertThat(store.findAll().contains(user), is(true));
        assertThat(store.findAll().size(), is(1));
        store.add(user);
        assertThat(store.findAll().size(), is(1));
        store.delete(user.getId());
    }

    /**
     * Test update
     */
    @Test
    public void whenUpdateUserThenUserInMemoryUpdated() {
        User user = new User("alex", "Alexander", "alex@mail.com", new Date());
        store.add(user);
        assertThat(store.findAll().contains(user), is(true));
        user.setLogin("aaalexxx");
        user.setEmail("aaalexxx@me.com");
        store.update(user.getId(), user);
        assertThat(store.findById(user.getId()).getLogin(), is("aaalexxx"));
        assertThat(store.findById(user.getId()).getEmail(), is("aaalexxx@me.com"));
        store.delete(user.getId());
    }

    /**
     * Test delete
     */
    @Test
    public void whenDeleteUserThenUserNotInMemory() {
        User user = new User("alex", "Alexander", "alex@mail.com", new Date());
        store.add(user);
        assertThat(store.findAll().contains(user), is(true));
        store.delete(user.getId());
        assertThat(store.findAll().contains(user), is(false));
        assertThat(store.findAll().isEmpty(), is(true));
        store.delete(user.getId());
    }

    /**
     * Test findById
     */
    @Test
    public void whenFindByIdUserThenReturnUserFromMemoryIfExists() {
        User user = new User("alex", "Alexander", "alex@mail.com", new Date());
        store.add(user);
        assertThat(store.findAll().contains(user), is(true));
        assertThat(store.findById(user.getId()), is(user));
        int fakeId = 3;
        assertThat(store.findById(fakeId), nullValue());
        store.delete(user.getId());
    }

    /**
     * Test findAll
     */
    @Test
    public void whenFindAllThenReturnAllUserFromMemory() {
        assertThat(store.findAll().isEmpty(), is(true));
        User user = new User("alex", "Alexander", "alex@mail.com", new Date());
        store.add(user);
        assertThat(store.findAll().isEmpty(), is(false));
        assertThat(store.findAll().size(), is(1));
        store.delete(user.getId());
    }
}