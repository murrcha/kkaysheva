package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * SimpleLinkedList Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleLinkedListTest {

    private SimpleLinkedList<Integer> container;

    @Before
    public void beforeTest() {
        container = new SimpleLinkedList<>();
        container.add(3);
        container.add(5);
        container.add(8);
    }

    /**
     * Test get
     */
    @Test
    public void whenGetElementsByIndexThenReturnValue() {
        assertThat(container.get(0), is(3));
        assertThat(container.get(1), is(5));
        assertThat(container.get(2), is(8));
    }

    /**
     * Test get
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetByInvalidIndexThenReturnException() {
        container.get(8);
    }

    /**
     * Test iterator
     */
    @Test
    public void whenGetIteratorThenReturnIterator() {
        Iterator<Integer> it = container.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Test iterator
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenCallNextAndModificationContainerThenReturnException() {
        Iterator<Integer> it = container.iterator();
        container.add(13);
        it.next();
    }

    /**
     * Test deleteFirst
     */
    @Test
    public void whenDeleteFirstElementThenReturnDeletedElement() {
        assertThat(container.deleteFirst(), is(3));
        assertThat(container.get(0), is(5));
        assertThat(container.get(1), is(8));
    }

    /**
     * Test deleteLast
     */
    @Test
    public void whenDeleteLastElementThenReturnDeletedElement() {
        assertThat(container.deleteLast(), is(8));
        assertThat(container.get(0), is(3));
        assertThat(container.get(1), is(5));
    }

    /**
     * Test deleteFirst
     */
    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFistElementInEmptyListThenReturnException() {
        for (int i = 0; i != 4; i++) {
            container.deleteFirst();
        }
    }

    /**
     * Test deleteLast
     */
    @Test(expected = NoSuchElementException.class)
    public void whenDeleteLastElementInEmptyListThenReturnException() {
        for (int i = 0; i != 4; i++) {
            container.deleteLast();
        }
    }
}