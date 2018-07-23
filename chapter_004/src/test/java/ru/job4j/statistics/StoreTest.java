package ru.job4j.statistics;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Store Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class StoreTest {

    private List<Store.User> previous;
    private List<Store.User> current;
    private Store store;
    private Info info;

    @Before
    public void beforeTest() {
        previous = new ArrayList<>();
        current = new ArrayList<>();
        previous.add(new Store.User(11, "Антон"));
        previous.add(new Store.User(22, "Мария"));
        previous.add(new Store.User(33, "Анна"));
        previous.add(new Store.User(44, "Иван"));
        previous.add(new Store.User(55, "Николай"));
        previous.add(new Store.User(66, "Ольга"));
        current.addAll(previous);
        current.set(0, new Store.User(11, "Андрей"));
        current.remove(4);
        current.remove(4);
        current.add(new Store.User(77, "Кирилл"));
        current.add(new Store.User(88, "Дарья"));
        current.add(new Store.User(99, "Илья"));
        store = new Store();
    }

    /**
     * Test diff
     */
    @Test
    public void whenCallDiffMethodThenReturnStatistics() {
        info = store.diff(previous, current);
        assertThat(info.getAddedCount(), is(3));
        assertThat(info.getChangedCount(), is(1));
        assertThat(info.getDeletedCount(), is(2));
    }

    /**
     * Test diff
     */
    @Test
    public void whenCallDiffMethodWithEmptyListsThenReturn0() {
        previous.clear();
        current.clear();
        info = store.diff(previous, current);
        assertThat(info.getAddedCount(), is(0));
        assertThat(info.getChangedCount(), is(0));
        assertThat(info.getDeletedCount(), is(0));
    }
}