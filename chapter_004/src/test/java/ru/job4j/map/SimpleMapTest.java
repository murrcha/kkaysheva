package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * SimpleMap Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleMapTest {

    private SimpleMap<String, String> map;

    @Before
    public void beforeTest() {
        map = new SimpleMap<>();
    }

    /**
     * Test insert
     */
    @Test
    public void whenInsert2ElementsThenSize2() {
        assertThat(map.insert("key1", "value1"), is(true));
        assertThat(map.insert("key2", "value2"), is(true));
        assertThat(map.getSize(), is(2));
    }

    /**
     * Test insert
     */
    @Test
    public void whenInsert2EqualsElementsThenSize1() {
        assertThat(map.insert("key1", "value1"), is(true));
        assertThat(map.insert("key1", "value2"), is(false));
        assertThat(map.getSize(), is(1));
    }

    /**
     * Test insert over size
     */
    @Test
    public void whenInsertOverSizeThenCallGrowTable() {
        SimpleMap<Integer, String> testMap = new SimpleMap<>(2);
        assertThat(testMap.insert(1, "one"), is(true));
        assertThat(testMap.insert(2, "two"), is(true));
        assertThat(testMap.getSize(), is(2));
        assertThat(testMap.insert(3, "three"), is(true));
        assertThat(testMap.getSize(), is(3));
    }

    /**
     * Test get
     */
    @Test
    public void whenGetElementByKeyThenReturnElementsOrNull() {
        assertThat(map.insert("key1", "value1"), is(true));
        assertThat(map.insert("key2", "value2"), is(true));
        assertThat(map.get("key1"), is("value1"));
        assertThat(map.get("key2"), is("value2"));
        assertThat(map.get("key3"), nullValue());
    }

    /**
     * Test delete
     */
    @Test
    public void whenDeleteElementByKeyThenReturnTrueOrFalse() {
        assertThat(map.insert("key1", "value1"), is(true));
        assertThat(map.insert("key2", "value2"), is(true));
        assertThat(map.delete("key3"), is(false));
        assertThat(map.getSize(), is(2));
        assertThat(map.delete("key1"), is(true));
        assertThat(map.getSize(), is(1));
        assertThat(map.delete("key2"), is(true));
        assertThat(map.getSize(), is(0));
        assertThat(map.delete("key3"), is(false));
    }

    /**
     * Test iterator
     */
    @Test
    public void whenGetIteratorThenReturnIterator() {
        assertThat(map.insert("key1", "value1"), is(true));
        assertThat(map.insert("key2", "value2"), is(true));
        Iterator<SimpleMap.Entry<String, String>> it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next().getKey(), is("key1"));
        assertThat(it.next().getKey(), is("key2"));
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Test iterator
     */
    @Test(expected = NoSuchElementException.class)
    public void whenCallNextInEmptyMapThenReturnException() {
        Iterator<SimpleMap.Entry<String, String>> it = map.iterator();
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    /**
     * Test iterator
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenCallNextAfterModificationMapThenReturnException() {
        assertThat(map.insert("key1", "value1"), is(true));
        assertThat(map.insert("key2", "value2"), is(true));
        Iterator<SimpleMap.Entry<String, String>> it = map.iterator();
        assertThat(map.insert("key3", "value3"), is(true));
        it.next();
    }
}