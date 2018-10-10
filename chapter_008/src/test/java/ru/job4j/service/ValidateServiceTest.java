package ru.job4j.service;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.store.MemoryStore;
import ru.job4j.store.Store;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

/**
 * ValidateService Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ValidateServiceTest {

    private Validate service;

    @Before
    public void before() {
        service = ValidateService.getInstance();
    }

    /**
     * Test add
     */
    @Test
    public void whenAddNewUserThenUserInMemory() {
        User user = new User("alex", "Alexander", "alex@mail.com", new Date());
        assertThat(service.add(user), is("User added"));
        assertThat(service.findAll().contains(user), is(true));
        assertThat(service.findAll().size(), is(1));
        assertThat(service.add(user), is("Error add user"));
        assertThat(service.findAll().size(), is(1));
        service.delete(user.getId());
    }

    /**
     * Test update
     */
    @Test
    public void whenUpdateUserThenUserUpdated() {
        User user = new User("alex", "Alexander", "alex@mail.com", new Date());
        assertThat(service.add(user), is("User added"));
        assertThat(service.findAll().contains(user), is(true));
        user.setLogin("aaalexxx");
        user.setEmail("aaalexxx@me.com");
        assertThat(service.update(user.getId(), user), is("User updated"));
        assertThat(service.findById(user.getId()).getLogin(), is("aaalexxx"));
        assertThat(service.findById(user.getId()).getEmail(), is("aaalexxx@me.com"));
        assertThat(service.update(5, user), is("Error update user"));
        service.delete(user.getId());
    }

    /**
     * Test delete
     */
    @Test
    public void whenDeleteUserThenUserNotInMemory() {
        User user = new User("alex", "Alexander", "alex@mail.com", new Date());
        assertThat(service.add(user), is("User added"));
        assertThat(service.findAll().contains(user), is(true));
        assertThat(service.delete(user.getId()), is("User deleted"));
        assertThat(service.findAll().contains(user), is(false));
        assertThat(service.findAll().isEmpty(), is(true));
        assertThat(service.delete(5), is("Error delete user"));
    }

    /**
     * Test findById
     */
    @Test
    public void whenFindByIdUserThenReturnUserFromMemoryIfExists() {
        User user = new User("alex", "Alexander", "alex@mail.com", new Date());
        assertThat(service.add(user), is("User added"));
        assertThat(service.findAll().contains(user), is(true));
        assertThat(service.findById(user.getId()), is(user));
        int fakeId = 3;
        assertThat(service.findById(fakeId), nullValue());
        service.delete(user.getId());
    }

    /**
     * Test findAll
     */
    @Test
    public void whenFindAllThenReturnAllUserFromMemory() {
        assertThat(service.findAll().isEmpty(), is(true));
        User user = new User("alex", "Alexander", "alex@mail.com", new Date());
        assertThat(service.add(user), is("User added"));
        assertThat(service.findAll().isEmpty(), is(false));
        assertThat(service.findAll().size(), is(1));
        service.delete(user.getId());
    }
}