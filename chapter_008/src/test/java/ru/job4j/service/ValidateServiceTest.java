package ru.job4j.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

    @After
    public void after() {
        service.deleteAll();
    }

    /**
     * Test add
     */
    @Test
    public void whenAddNewUserThenUserInMemory() {
        User user = new User("alex", "Alexander", "alex@mail.com", new Date());
        String result = service.add(user);
        assertThat(result, is("User added"));
        assertThat(service.findAll().size(), is(1));
    }

    /**
     * Test update
     */
    @Test
    public void whenUpdateUserThenUserUpdated() {
        User user = new User("alex", "Alexander", "alex@mail.com", new Date());
        assertThat(service.add(user), is("User added"));
        assertThat(service.findAll().size(), is(1));
        user = service.findAll().iterator().next();
        user.setLogin("aaalexxx");
        user.setEmail("aaalexxx@me.com");
        assertThat(service.update(user.getId(), user), is("User updated"));
        assertThat(service.findById(user.getId()).getLogin(), is("aaalexxx"));
        assertThat(service.findById(user.getId()).getEmail(), is("aaalexxx@me.com"));
        assertThat(service.update(5, user), is("Error update user"));
    }

    /**
     * Test delete
     */
    @Test
    public void whenDeleteUserThenUserNotInMemory() {
        User user = new User("alex", "Alexander", "alex@mail.com", new Date());
        assertThat(service.add(user), is("User added"));
        user = service.findAll().iterator().next();
        assertThat(service.findAll().size(), is(1));
        assertThat(service.delete(user.getId()), is("User deleted"));
        assertThat(service.findAll().size(), is(0));
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
        user = service.findAll().iterator().next();
        assertThat(service.findAll().size(), is(1));
        assertThat(service.findById(user.getId()).getLogin(), is(user.getLogin()));
        int fakeId = 3;
        assertThat(service.findById(fakeId), nullValue());
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
    }
}