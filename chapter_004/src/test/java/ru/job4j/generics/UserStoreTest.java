package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * UserStore Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since
 */
public class UserStoreTest {

    /**
     * Test create
     */
    @Test
    public void whenCreateUserStoreWithSize3ThenReturnSize3() {
        UserStore testUserStore = new UserStore(3);
        assertThat(testUserStore.getElements().getSize(), is(3));
    }

    /**
     * Test add and findById
     */
    @Test
    public void whenAddUserToUserStoreAndFindByIdThenReturnThisUser() {
        UserStore testUserStore = new UserStore(3);
        User userOne = new User("1");
        User userTwo = new User("2");
        testUserStore.add(userOne);
        testUserStore.add(userTwo);
        assertThat(testUserStore.findById("1"), is(userOne));
        assertThat(testUserStore.findById("2"), is(userTwo));
        assertThat(testUserStore.findById("3"), nullValue());
    }

    /**
     * Test replace
     */
    @Test
    public void whenReplaceUserByIdThenUserIsReplaced() {
        UserStore testUserStore = new UserStore(2);
        User userOne = new User("1");
        User userTwo = new User("2");
        User userThree = new User("2");
        testUserStore.add(userOne);
        testUserStore.add(userTwo);
        assertThat(testUserStore.replace("2", userThree), is(true));
        assertThat(testUserStore.replace("1", userThree), is(false));
        assertThat(testUserStore.findById("1"), is(userOne));
        assertThat(testUserStore.findById("2"), is(userThree));
    }

    /**
     * Test delete
     */
    @Test
    public void whenDeleteUserFromUserStoreThenUserIsDeleted() {
        UserStore testUserStore = new UserStore(2);
        User userOne = new User("1");
        User userTwo = new User("2");
        testUserStore.add(userOne);
        testUserStore.add(userTwo);
        assertThat(testUserStore.delete("1"), is(true));
        assertThat(testUserStore.findById("1"), nullValue());
        assertThat(testUserStore.findById("2"), is(userTwo));
    }

}