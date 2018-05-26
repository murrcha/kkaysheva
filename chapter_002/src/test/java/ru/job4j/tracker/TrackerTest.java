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
        assertThat(testTracker.findAll().size(), is(1));
        assertThat(testTracker.findAll().get(0), is(testItem));
        assertThat(testTracker.findAll().get(0).getName(), is("name1"));
        assertThat(testTracker.findAll().get(0).getDescription(), is("description1"));
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
        assertThat(testTracker.findAll().size(), is(1));
        assertThat(testTracker.findById(previousItem.getId()).getName(), is("name2"));
        assertThat(testTracker.findById(previousItem.getId()).getDescription(), is("description2"));
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
        assertThat(testTracker.findAll().size(), is(1));
        assertThat(testTracker.findAll().get(0).getName(), is("name2"));
        assertThat(testTracker.findAll().get(0).getDescription(), is("description2"));
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
        assertThat(testTracker.findAll().size(), is(2));
    }

    /**
     * Test findAll
     */
    @Test
    public void whenFindAllInEmptyTrackerThenReturnEmptyValue() {
        Tracker testTracker = new Tracker();
        assertThat(testTracker.findAll().size(), is(0));
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
        assertThat(testTracker.findByName("test").size(), is(0));
    }

    /**
     * Test findByName
     */
    @Test
    public void whenFindByNameThenReturnRelevantItems() {
        Tracker testTracker = new Tracker();
        Item firstItem = new Item("test", "desc1", 111L);
        Item secondItem = new Item("name", "desc2", 222L);
        Item thirdItem = new Item("test1", "desc1", 111L);
        testTracker.add(firstItem);
        testTracker.add(secondItem);
        testTracker.add(thirdItem);
        assertThat(testTracker.findByName("test").size(), is(2));
        assertThat(testTracker.findByName("test").get(0).getName(), is("test"));
        assertThat(testTracker.findByName("test").get(1).getName(), is("test1"));
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
