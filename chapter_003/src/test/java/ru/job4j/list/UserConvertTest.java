package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * UserConvert Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class UserConvertTest {

    /**
     * Test process
     */
    @Test
    public void whenUserListConvertThenReturnHashMap() {
        UserConvert converter = new UserConvert();
        List<User> list = new ArrayList<>();
        list.add(new User(1, "Иван", "Омск"));
        list.add(new User(2, "Анна", "Москва"));
        list.add(new User(3, "Ольга", "Орск"));
        list.add(new User(3, "Олег", "Орел"));
        HashMap<Integer, User> result =  converter.process(list);
        assertThat(result.get(1).getName(), is("Иван"));
        assertThat(result.get(1).getCity(), is("Омск"));
        assertThat(result.get(2).getName(), is("Анна"));
        assertThat(result.get(2).getCity(), is("Москва"));
        assertThat(result.get(3).getName(), is("Олег"));
        assertThat(result.get(3).getCity(), is("Орел"));
    }

    /**
     * Test process
     */
    @Test
    public void whenEmptyUserListConvertThenReturnEmptyHashMap() {
        UserConvert converter = new UserConvert();
        List<User> list = new ArrayList<>();
        HashMap<Integer, User> result =  converter.process(list);
        assertThat(result.isEmpty(), is(true));
    }
}
