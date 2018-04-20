package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Tracker Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class TrackerTest {

    /**
     * Test add
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker testTracker = new Tracker();
        Item testItem = new Item("name1", "description1", 123L);
        testTracker.add(testItem);
        assertThat(testTracker.findAll()[0], is(testItem));
    }

    /**
     * Test replace
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker testTracker = new Tracker();
        Item previousItem = new Item("name1", "description1", 123L);
        testTracker.add(previousItem);
        Item nextItem = new Item("name2", "description2", 1234L);
        nextItem.setId(previousItem.getId());
        testTracker.replace(previousItem.getId(), nextItem);
        assertThat(testTracker.findById(previousItem.getId()).getName(), is("name2"));
    }

    /**
     * Test delete
     */
    @Test
    public void whenDeleteOneItemThenNoThisItemInTracker() {
        Tracker testTracker = new Tracker();
        Item firstItem = new Item("name1", "description1", 111L);
        Item secondItem = new Item("name2", "description2", 222L);
        testTracker.add(firstItem);
        testTracker.add(secondItem);
        testTracker.delete(firstItem.getId());
        assertThat(testTracker.findAll().length, is(1));
        assertThat(testTracker.findAll()[0].getName(), is("name2"));
    }

    /**
     * Test findAll
     */
    @Test
    public void whenFindAllItemThenReturnAllItem() {
        Tracker testTracker = new Tracker();
        Item firstTestItem = new Item("name1", "desc1", 111L);
        Item secondTestItem = new Item("name2", "desc2", 222L);
        testTracker.add(firstTestItem);
        testTracker.add(secondTestItem);
        assertThat(testTracker.findAll().length, is(2));
    }

    /**
     * Test findAll
     */
    @Test
    public void whenFindAllInEmptyTrackerThenReturnEmptyValue() {
        Tracker testTracker = new Tracker();
        assertThat(testTracker.findAll().length, is(0));
    }

    /**
     * Test findByName
     */
    @Test
    public void whenFindByFakeNameThenReturnEmptyValue() {
        Tracker testTracker = new Tracker();
        Item firstItem = new Item("name1", "desc1", 111L);
        Item secondItem = new Item("name2", "desc2", 222L);
        Item thirdItem = new Item("name3", "desc3", 333L);
        testTracker.add(firstItem);
        testTracker.add(secondItem);
        testTracker.add(thirdItem);
        assertThat(testTracker.findByName("test").length, is(0));
    }

    /**
     * Test findByName
     */
    @Test
    public void whenFindByNameThenReturnRelevantItems() {
        Tracker testTracker = new Tracker();
        Item firstItem = new Item("test", "desc1", 111L);
        Item secondItem = new Item("name", "desc2", 222L);
        Item thirdItem = new Item("test", "desc1", 111L);
        testTracker.add(firstItem);
        testTracker.add(secondItem);
        testTracker.add(thirdItem);
        assertThat(testTracker.findByName("test").length, is(2));
        assertThat(testTracker.findByName("test")[0].getName(), is("test"));
        assertThat(testTracker.findByName("test")[1].getName(), is("test"));
    }

    /**
     * Test findById
     */
    @Test
    public void whenFindByFakeIdThenReturnNullValue() {
        Tracker testTracker = new Tracker();
        Item firstItem = new Item("first", "desc1", 111L);
        Item secondItem = new Item("second", "desc2", 222L);
        testTracker.add(firstItem);
        testTracker.add(secondItem);
        String fakeId = firstItem.getId() + secondItem.getId();
        assertThat(testTracker.findById(fakeId), nullValue());
    }

    /**
     * Test findById
     */
    @Test
    public void whenFindByRealIdThenReturnRelevantItem() {
        Tracker testTracker = new Tracker();
        Item firstItem = new Item("first", "desc1", 111L);
        Item secondItem = new Item("second", "desc2", 222L);
        testTracker.add(firstItem);
        testTracker.add(secondItem);
        assertThat(testTracker.findById(secondItem.getId()), is(secondItem));
    }
}
