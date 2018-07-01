package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * SimpleArrayList Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleArrayListTest {

    /**
     * Test create instance
     */
    @Test
    public void whenCreateContainerWithoutSizeThenSetDefaultSize10() {
        SimpleArrayList<Integer> container = new SimpleArrayList<>();
        assertThat(container.getSize(), is(10));
    }

    /**
     * Test create instance
     */
    @Test
    public void whenCreateContainerWithSize3ThenSetSize3() {
        SimpleArrayList<Integer> container = new SimpleArrayList<>(3);
        assertThat(container.getSize(), is(3));
    }

    /**
     * Test add and get
     */
    @Test
    public void whenAddNewValueThenContainerContainNewValue() {
        SimpleArrayList<String> container = new SimpleArrayList<>(2);
        container.add("13");
        container.add("abc");
        assertThat(container.get(0), is("13"));
        assertThat(container.get(1), is("abc"));
    }

    /**
     * Test add
     */
    @Test
    public void whenAddValueOverSizeThenSizeGrow() {
        SimpleArrayList<String> container = new SimpleArrayList<>(2);
        container.add("1");
        container.add("2");
        assertThat(container.getSize(), is(2));
        container.add("3");
        assertThat(container.getSize(), is(12));
    }

    /**
     * Test get
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetByInvalidIndexThenReturnException() {
        SimpleArrayList<Integer> container = new SimpleArrayList<>(1);
        container.add(1);
        assertThat(container.get(0), is(1));
        container.get(2);
    }

    /**
     * Test iterator
     */
    @Test
    public void whenGetIteratorThenReturnIterator() {
        SimpleArrayList<Integer> container = new SimpleArrayList<>(2);
        container.add(1);
        container.add(2);
        Iterator<Integer> it = container.iterator();
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
    @Test(expected = NoSuchElementException.class)
    public void whenHasNextFalseAndCallNextThenReturnException() {
        SimpleArrayList<Integer> container = new SimpleArrayList<>(1);
        Iterator<Integer> it = container.iterator();
        it.next();
        it.next();
    }

    /**
     * Test iterator
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenCallNextAndModificationContainerThenReturnException() {
        SimpleArrayList<Integer> container = new SimpleArrayList<>(2);
        Iterator<Integer> it = container.iterator();
        container.add(1);
        it.next();
    }
}