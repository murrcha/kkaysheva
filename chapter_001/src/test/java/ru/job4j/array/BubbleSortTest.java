package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * BubbleSort Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class BubbleSortTest {

    /**
     * Test sort
     */
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        int[] input = new int[] {10, 3, 5, 2, 1, 4, 7, 6, 9, 8};
        BubbleSort sorter = new BubbleSort();
        int[] result = sorter.sort(input);
        int[] expected = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertThat(result, is(expected));
    }
}
