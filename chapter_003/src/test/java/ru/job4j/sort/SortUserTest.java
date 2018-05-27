package ru.job4j.sort;

import org.junit.Test;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * SortUser Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SortUserTest {

    /**
     * Test sort
     */
    @Test
    public void whenSortByAgeThenReturnSortedSet() {
        SortUser sorter = new SortUser();
        List<User> users = Arrays.asList(
                new User("Kat", 25),
                new User("Ann", 30),
                new User("Dan", 20),
                new User("Sam", 20),
                new User("Sam", 15)
        );
        Set<User> result = sorter.sort(users);
        assertThat(result.toString(), is(
                "[User: Sam, 15, User: Dan, 20, User: Sam, 20, User: Kat, 25, User: Ann, 30]"));
    }
}
