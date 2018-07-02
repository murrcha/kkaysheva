package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * SimpleList Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleListTest {

    private SimpleList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    /**
     * Test get
     */
    @Test
    public void whenAddThreeElementsThenUseGetOneResultThree() {
        assertThat(list.get(0), is(3));
    }

    /**
     * Test get
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetByInvalidIndexThenReturnException() {
        list.get(3);
    }

    /**
     * Test getSize
     */
    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    /**
     * Test delete
     */
    @Test
    public void whenDeleteElementsThenReturnDeletedElementAndChangeSize() {
        Integer deletedElement = list.delete();
        assertThat(deletedElement, is(3));
        assertThat(list.getSize(), is(2));
        deletedElement = list.delete();
        assertThat(deletedElement, is(2));
        assertThat(list.getSize(), is(1));
        assertThat(list.get(0), is(1));
    }

    /**
     * Test delete
     */
    @Test(expected = NoSuchElementException.class)
    public void whenDeleteElementInEmptyListThenReturnException() {
        SimpleList<Integer> testList = new SimpleList<>();
        testList.delete();
    }
}