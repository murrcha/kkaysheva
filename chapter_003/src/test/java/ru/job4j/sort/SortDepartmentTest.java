package ru.job4j.sort;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * SortDepartment Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SortDepartmentTest {

    /**
     * Test input
     */
    private static final String[] INPUT = {
            "K1\\SK1",
            "K1\\SK2",
            "K1\\SK1\\SSK1",
            "K1\\SK1\\SSK2",
            "K2",
            "K2\\SK1\\SSK1",
            "K2\\SK1\\SSK2"
    };

    /**
     * Test sortAscending
     */
    @Test
    public void whenSortAscendingThenReturnSortedArray() {
        SortDepartment sorter = new SortDepartment();
        String[] result = sorter.sortAscending(INPUT);
        assertThat(result, is(new String[] {
                "K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        }));
    }

    /**
     * Test sortDescending
     */
    @Test
    public void whenSortDescendingThenReturnSortedArray() {
        SortDepartment sorter = new SortDepartment();
        String[] result = sorter.sortDescending(INPUT);
        assertThat(result, is(new String[] {
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K1",
                "K1\\SK2",
                "K1\\SK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK1"
        }));
    }
}
