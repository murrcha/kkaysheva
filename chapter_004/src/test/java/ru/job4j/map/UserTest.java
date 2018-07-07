package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * User Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class UserTest {

    /**
     * Test
     */
    @Test
    public void whenNotOverrideEqualsAndHashcodeThenUniqueEveryInstanceOfClassUser() {
        Calendar date = Calendar.getInstance();
        date.set(1980, 01, 01);
        User userOne = new User("Константин", 0, date);
        User userTwo = new User("Константинб", 0, date);
        Map<User, Object> map = new HashMap<>();
        map.put(userOne, new Object());
        map.put(userTwo, new Object());
        assertThat(map.size(), is(2));
    }
}