package tracker;

import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.stringContainsInOrder;
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
     * Test add
     */
    @Ignore
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker testTracker = new Tracker();
        Item testItem = new Item("name2", "description2");
        Item result = testTracker.add(testItem);
        List<Item> items = testTracker.findAll();
        assertThat(items.isEmpty(), is(false));
        assertThat(result.getName(), is("name1"));
        assertThat(testTracker.findAll().size(), is(1));
        assertThat(testTracker.findAll().get(0), is(result));
        assertThat(testTracker.findAll().get(0).getName(), is("name1"));
        assertThat(testTracker.findAll().get(0).getDescription(), is("description1"));
    }

    /**
     * Test replace
     */
    @Ignore
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker testTracker = new Tracker();
        Item previousItem = new Item("name1", "description1");
        testTracker.add(previousItem);
        Item nextItem = new Item("name2", "description2");
        nextItem.setId(previousItem.getId());
        testTracker.replace(previousItem.getId(), nextItem);
        assertThat(testTracker.findAll().size(), is(1));
        assertThat(testTracker.findById(previousItem.getId()).getName(), is("name2"));
        assertThat(testTracker.findById(previousItem.getId()).getDescription(), is("description2"));
    }

    /**
     * Test delete
     */
    @Ignore
    @Test
    public void whenDeleteOneItemThenNoThisItemInTracker() {
        Tracker testTracker = new Tracker();
        Item firstItem = new Item("name1", "description1");
        Item secondItem = new Item("name2", "description2");
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
    @Ignore
    @Test
    public void whenFindAllItemThenReturnAllItem() {
        Tracker testTracker = new Tracker();
        Item firstTestItem = new Item("name1", "desc1");
        Item secondTestItem = new Item("name2", "desc2");
        testTracker.add(firstTestItem);
        testTracker.add(secondTestItem);
        //assertThat(testTracker.findAll().size(), is(2));
    }

    /**
     * Test findAll
     */
    @Ignore
    @Test
    public void whenFindAllInEmptyTrackerThenReturnEmptyValue() {
        Tracker testTracker = new Tracker();
        assertThat(testTracker.findAll().size(), is(0));
    }

    /**
     * Test findByName
     */
    @Ignore
    @Test
    public void whenFindByFakeNameThenReturnEmptyValue() {
        Tracker testTracker = new Tracker();
        Item firstItem = new Item("name1", "desc1");
        Item secondItem = new Item("name2", "desc2");
        Item thirdItem = new Item("name3", "desc3");
        testTracker.add(firstItem);
        testTracker.add(secondItem);
        testTracker.add(thirdItem);
        assertThat(testTracker.findByName("test").size(), is(0));
    }

    /**
     * Test findByName
     */
    @Ignore
    @Test
    public void whenFindByNameThenReturnRelevantItems() {
        Tracker testTracker = new Tracker();
        Item firstItem = new Item("test", "desc1");
        Item secondItem = new Item("name", "desc2");
        Item thirdItem = new Item("test1", "desc1");
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
    @Ignore
    @Test
    public void whenFindByFakeIdThenReturnNullValue() {
        Tracker testTracker = new Tracker();
        Item firstItem = new Item("first", "desc1");
        Item secondItem = new Item("second", "desc2");
        testTracker.add(firstItem);
        testTracker.add(secondItem);
        int fakeId = firstItem.getId() + secondItem.getId();
        assertThat(testTracker.findById(fakeId), nullValue());
    }

    /**
     * Test findById
     */
    @Ignore
    @Test
    public void whenFindByRealIdThenReturnRelevantItem() {
        Tracker testTracker = new Tracker();
        Item firstItem = new Item("first", "desc1");
        Item secondItem = new Item("second", "desc2");
        testTracker.add(firstItem);
        testTracker.add(secondItem);
        assertThat(testTracker.findById(secondItem.getId()), is(secondItem));
    }
    @Ignore
    @Test
    public void whenCreateTrackerThenTestConnection() {
        try (Tracker tracker = new Tracker()) {
            List<Item> itemNames = tracker.findByName("name");
            itemNames.forEach(it -> System.out.println(it.getName()));
            Item item1 = new Item("name44", "desc44");
            tracker.replace(2, item1);
            Item item = tracker.findById(2);
            tracker.delete(2);
            List<Item> items = tracker.findAll();
            items.forEach(it -> System.out.println(it.getName()));
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
