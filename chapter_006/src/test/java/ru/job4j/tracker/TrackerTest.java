package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Tracker Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class TrackerTest {

    /**
     * tracker
     */
    private Tracker tracker;

    /**
     * init tracker and clear table items for test
     */
    @Before
    public void before() {
        tracker = new Tracker();
        tracker.deleteAll();
    }

    /**
     * close tracker
     */
    @After
    public void after() {
        if (tracker != null) {
            try {
                tracker.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Test add
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Item resultOne = tracker.add(new Item("name1", "description1"));
        Item resultTwo = tracker.add(new Item("name2", "description2"));
        assertThat(tracker.findAll().size(), is(2));
        assertThat(tracker.findById(resultOne.getId()).getName(), is("name1"));
        assertThat(tracker.findById(resultTwo.getId()).getName(), is("name2"));
        assertThat(tracker.findAll().get(0).getDescription(), is("description1"));
        assertThat(tracker.findAll().get(1).getDescription(), is("description2"));
    }

    /**
     * Test replace
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Item previousItem = tracker.add(new Item("name1", "description1"));
        Item nextItem = new Item("name2", "description2");
        tracker.replace(previousItem.getId(), nextItem);
        assertThat(tracker.findAll().size(), is(1));
        assertThat(tracker.findById(previousItem.getId()).getName(), is("name2"));
        assertThat(tracker.findById(previousItem.getId()).getDescription(), is("description2"));
    }

    /**
     * Test delete
     */
    @Test
    public void whenDeleteOneItemThenNoThisItemInTracker() {
        Item firstItem = tracker.add(new Item("name1", "description1"));
        Item secondItem = tracker.add(new Item("name2", "description2"));
        tracker.delete(firstItem.getId());
        assertThat(tracker.findAll().size(), is(1));
        assertThat(tracker.findById(secondItem.getId()).getName(), is("name2"));
        assertThat(tracker.findById(secondItem.getId()).getDescription(), is("description2"));
    }

    /**
     * Test findAll
     */
    @Test
    public void whenFindAllItemThenReturnAllItem() {
        tracker.add(new Item("name1", "desc1"));
        tracker.add(new Item("name2", "desc2"));
        assertThat(tracker.findAll().size(), is(2));
    }

    /**
     * Test findAll
     */
    @Test
    public void whenFindAllInEmptyTrackerThenReturnEmptyValue() {
        assertThat(tracker.findAll().size(), is(0));
    }

    /**
     * Test findByName
     */
    @Test
    public void whenFindByFakeNameThenReturnEmptyValue() {
        tracker.add(new Item("name1", "desc1"));
        tracker.add(new Item("name2", "desc2"));
        tracker.add(new Item("name3", "desc3"));
        assertThat(tracker.findByName("test").size(), is(0));
    }

    /**
     * Test findByName
     */
    @Test
    public void whenFindByNameThenReturnRelevantItems() {
        tracker.add(new Item("test", "desc1"));
        tracker.add(new Item("name", "desc2"));
        tracker.add(new Item("test1", "desc1"));
        assertThat(tracker.findByName("test").size(), is(2));
        assertThat(tracker.findByName("test").get(0).getName(), is("test"));
        assertThat(tracker.findByName("test").get(1).getName(), is("test1"));
    }

    /**
     * Test findById
     */
    @Test
    public void whenFindByFakeIdThenReturnNullValue() {
        Item firstItem = new Item("first", "desc1");
        Item secondItem = new Item("second", "desc2");
        tracker.add(firstItem);
        tracker.add(secondItem);
        int fakeId = firstItem.getId() + secondItem.getId();
        assertThat(tracker.findById(fakeId), nullValue());
    }

    /**
     * Test findById
     */
    @Test
    public void whenFindByRealIdThenReturnRelevantItem() {
        tracker.add(new Item("first", "desc1"));
        Item secondItem = tracker.add(new Item("second", "desc2"));
        assertThat(tracker.findById(secondItem.getId()).getName(), is("second"));
        assertThat(tracker.findById(secondItem.getId()).getDescription(), is("desc2"));
    }
}
