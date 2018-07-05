package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * SimpleListSet Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleListSetTest {

    private SimpleListSet<Integer> set;

    @Before
    public void beforeTest() {
        set = new SimpleListSet<>();
    }

    /**
     * Test add and iterator
     */
    @Test
    public void whenAddEqualsElementsThenSetContainsOnlyUniqueElements() {
        set.add(1);
        set.add(2);
        set.add(1);
        set.add(2);
        set.add(1);
        Iterator<Integer> it = set.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Test iterator
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenGetIteratorAndAddElementThenReturnException() {
        set.add(1);
        Iterator<Integer> it = set.iterator();
        set.add(2);
        it.next();
    }

    /**
     * Test iterator
     */
    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptySetAndCallNextThenReturnException() {
        Iterator<Integer> it = set.iterator();
        it.next();
    }
}