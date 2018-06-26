package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * SimpleArray Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleArrayTest {

    /**
     * Test create SimpleArray
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenCreateSimpleArrayWith0SizeThenReturnEmptyArray() {
        SimpleArray<String> testArray = new SimpleArray<>(0);
        assertThat(testArray.getSize(), is(0));
        testArray.add("test");
    }

    /**
     * Test create SimpleArray
     */
    @Test
    public void whenCreateSimpleArrayWithInvalidSizeThenSetDefaultSize10() {
        SimpleArray<Integer> testArray = new SimpleArray<Integer>(-1);
        assertThat(testArray.getSize(), is(10));
    }

    /**
     * Test add and get
     */
    @Test
    public void whenAddElementAndGetElementThenReturnAddedElement() {
        SimpleArray<Integer> integerArray = new SimpleArray<>(2);
        integerArray.add(3);
        integerArray.add(5);
        assertThat(integerArray.get(0), is(3));
        assertThat(integerArray.get(1), is(5));
    }

    /**
     * Test add
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddElementAndOverflowSizeThenReturnException() {
        SimpleArray<Integer> integerArray = new SimpleArray<>(2);
        integerArray.add(3);
        integerArray.add(5);
        integerArray.add(8);
    }

    /**
     * Test get
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetElementByInvalidIndexThenReturnException() {
        SimpleArray<Integer> integerArray = new SimpleArray<>(2);
        integerArray.add(3);
        integerArray.add(5);
        integerArray.get(2);
    }

    /**
     * Test set
     */
    @Test
    public void whenSetNewValueAndGetThisElementThenReturnElementWithNewValue() {
        SimpleArray<Integer> integerArray = new SimpleArray<>(2);
        integerArray.add(3);
        integerArray.add(5);
        integerArray.set(0, 33);
        integerArray.set(1, 55);
        assertThat(integerArray.get(0), is(33));
        assertThat(integerArray.get(1), is(55));
    }

    /**
     * Test set
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetNewValueByInvalidIndexThenReturnException() {
        SimpleArray<Integer> integerArray = new SimpleArray<>(1);
        integerArray.add(3);
        integerArray.set(1, 5);
    }

    /**
     * Test delete
     */
    @Test
    public void whenDeleteElementByValidIndexThenDeletingSuccess() {
        SimpleArray<Double> doubleArray = new SimpleArray<>(2);
        doubleArray.add(3.0);
        doubleArray.add(5.0);
        doubleArray.delete(0);
        assertThat(doubleArray.get(0), is(5.0));
    }

    /**
     * Test delete
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenDeleteElementByInvalidIndexThenReturnException() {
        SimpleArray<Double> doubleArray = new SimpleArray<>(1);
        doubleArray.delete(2);
   }

    /**
     * Test iterator
     */
   @Test
    public void whenGetIteratorThenReturnIterator() {
        SimpleArray<Integer> integerArray = new SimpleArray<>(2);
        integerArray.add(3);
        integerArray.add(5);
        Iterator<Integer> it = integerArray.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(false));
   }

    /**
     * Test iterator
     */
   @Test(expected = NoSuchElementException.class)
    public void whenHasNextFalseAndCallNextThenReturnException() {
       SimpleArray<Integer> integerArray = new SimpleArray<>(1);
       Iterator<Integer> it = integerArray.iterator();
       it.next();
       it.next();
   }
}