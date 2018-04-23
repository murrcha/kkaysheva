package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * StartUI Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class StartUITest {

    /**
     * Test add new item
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker testTracker = new Tracker();
        Input testInput = new StubInput(new String[] {"0", "name1", "desc1", "6"});
        new StartUI(testInput, testTracker).init();
        assertThat(testTracker.findAll()[0].getName(), is("name1"));
    }

    /**
     * Test edit item
     */
    @Test
    public void whenUserEditItemThenTrackerHasUpdateItem() {
        Tracker testTracker = new Tracker();
        Item testItem = testTracker.add(new Item("test", "test"));
        String testId = testItem.getId();
        Input testInput = new StubInput(new String[] {"2", testId, "name1", "desc1", "6"});
        new StartUI(testInput, testTracker).init();
        assertThat(testTracker.findById(testId).getName(), is("name1"));
    }

    /**
     * Test delete item
     */
    @Test
    public void whenUserDeleteItemThenTrackerNotHaveThisItem() {
        Tracker testTracker = new Tracker();
        Item testItem = testTracker.add(new Item("test", "test"));
        String testId = testItem.getId();
        Input testInput = new StubInput(new String[] {"3", testId, "6"});
        new StartUI(testInput, testTracker).init();
        assertThat(testTracker.findById(testId), nullValue());
    }
}
