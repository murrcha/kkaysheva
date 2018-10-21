package ru.job4j.store;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.service.User;

import java.util.Collection;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

/**
 * DBStore Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class DBStoreTest {

    private Store<User> store;

    @Before
    public void before() {
        store = DBStore.getInstance();
        store.deleteAll();
    }

    /**
     * Test add
     */
    @Test
    public void whenAddUserInDBStoreThenUserInDBStore() {
        User user = new User("alex", "Alexander", "alex@mail.com", "123", 2, new Date());
        int id = store.add(user);
        assertThat(store.findById(id).getLogin(), is(user.getLogin()));
        assertThat(store.findById(id).getName(), is(user.getName()));
        assertThat(store.findById(id).getEmail(), is(user.getEmail()));
    }

    /**
     * Test update
     */
    @Test
    public void whenUpdateUserInDBStoreThenUserUpdated() {
        User user = new User("katy", "Katya", "kat@mail.com", "123", 2, new Date());
        int id = store.add(user);
        assertThat(store.findById(id).getLogin(), is(user.getLogin()));
        user.setLogin("katusha");
        user.setName("Katerina");
        user.setEmail("Ekaterina@mail.com");
        store.update(id, user);
        assertThat(store.findById(id).getLogin(), is(user.getLogin()));
        assertThat(store.findById(id).getName(), is(user.getName()));
        assertThat(store.findById(id).getEmail(), is(user.getEmail()));
    }

    /**
     * Test delete
     */
    @Test
    public void whenDeleteUserFromDBStoreThenUserNotExistsInDBStore() {
        User user = new User("katy", "Katya", "kat@mail.com", "123", 2, new Date());
        int id = store.add(user);
        assertThat(store.findById(id).getLogin(), is(user.getLogin()));
        store.delete(id);
        assertThat(store.findById(id), nullValue());
    }

    /**
     * Test findById
     */
    @Test
    public void whenFindByIdUserThenReturnUserOrNull() {
        User user = new User("katy", "Katya", "kat@mail.com", "123", 2, new Date());
        int id = store.add(user);
        assertThat(store.findById(id).getLogin(), is(user.getLogin()));
        assertThat(store.findById(0), nullValue());
    }

    /**
     * Test findAll
     */
    @Test
    public void whenFindAllUsersThenReturnCollectionUsers() {
        store.deleteAll();
        User user = new User("katy", "Katya", "kat@mail.com", "123", 2, new Date());
        store.add(user);
        Collection<User> users = store.findAll();
        assertThat(users.isEmpty(), is(false));
        assertThat(users.size(), is(1));
    }

    /**
     * Test deleteAll
     */
    @Test
    public void whenClearTableUsersThenDBStoreEmpty() {
        User user = new User("katy", "Katya", "kat@mail.com", "123", 2, new Date());
        store.add(user);
        store.deleteAll();
        assertThat(store.findAll().isEmpty(), is(true));
    }
}