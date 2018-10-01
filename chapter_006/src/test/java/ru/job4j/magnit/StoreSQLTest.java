package ru.job4j.magnit;

import org.junit.Test;
import ru.job4j.magnit.pojo.Entry;

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
        List<Entry> entries;
        StoreSQL store = new StoreSQL();
        store.generate(10);
        entries = store.getEntries();
        store.close();
        assertThat(entries.isEmpty(), is(false));
        assertThat(entries.size(), is(10));
    }

    /**
     * Test exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenGenerateRowsIsFailedThenReturnException() {
        StoreSQL store = new StoreSQL();
        store.generate(-10);
        store.close();
    }
}