package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * RoleStore Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class RoleStoreTest {

    /**
     * Test create
     */
    @Test
    public void whenCreateRoleStoreWithSize3ThenReturnSize3() {
        AbstractStore<Role> testRoleStore = new RoleStore(3);
        assertThat(testRoleStore.getElements().getSize(), is(3));
    }

    /**
     * Test add and findById
     */
    @Test
    public void whenAddRoleToRoleStoreAndFindByIdThenReturnThisRole() {
        RoleStore testRoleStore = new RoleStore(3);
        Role roleOne = new Role("1");
        Role roleTwo = new Role("2");
        testRoleStore.add(roleOne);
        testRoleStore.add(roleTwo);
        assertThat(testRoleStore.findById("1"), is(roleOne));
        assertThat(testRoleStore.findById("2"), is(roleTwo));
        assertThat(testRoleStore.findById("3"), nullValue());
    }

    /**
     * Test replace
     */
    @Test
    public void whenReplaceRoleByIdThenRoleIsReplaced() {
        RoleStore testRoleStore = new RoleStore(2);
        Role roleOne = new Role("1");
        Role roleTwo = new Role("2");
        Role roleThree = new Role("2");
        testRoleStore.add(roleOne);
        testRoleStore.add(roleTwo);
        assertThat(testRoleStore.replace("2", roleThree), is(true));
        assertThat(testRoleStore.replace("1", roleThree), is(false));
        assertThat(testRoleStore.findById("1"), is(roleOne));
        assertThat(testRoleStore.findById("2"), is(roleThree));
    }

    /**
     * Test delete
     */
    @Test
    public void whenDeleteRoleFromRoleStoreThenRoleIsDeleted() {
        RoleStore testRoleStore = new RoleStore(2);
        Role roleOne = new Role("1");
        Role roleTwo = new Role("2");
        testRoleStore.add(roleOne);
        testRoleStore.add(roleTwo);
        assertThat(testRoleStore.delete("1"), is(true));
        assertThat(testRoleStore.findById("1"), nullValue());
        assertThat(testRoleStore.findById("2"), is(roleTwo));
    }
}