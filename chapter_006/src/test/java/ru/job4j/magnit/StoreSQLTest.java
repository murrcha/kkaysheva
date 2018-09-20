package ru.job4j.magnit;

import org.junit.Test;
import ru.job4j.magnit.pojo.Entry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * StoreSQLTest
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class StoreSQLTest {

    /**
     * Test generate
     */
    @Test
    public void whenGenerateRecordsInTableThenTableContentThisRecords() {
        List<Entry> entries = new ArrayList<>();
        try (StoreSQL store = new StoreSQL()) {
            store.generate(10);
            entries = store.getEntries();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertThat(entries.isEmpty(), is(false));
        assertThat(entries.size(), is(10));
    }

    /**
     * Test exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenGenerateRowsIsFailedThenReturnException() {
        try (StoreSQL store = new StoreSQL()) {
            store.generate(-10);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}