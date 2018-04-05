package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * ArrayMerge Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ArrayMergeTest {

    /**
     * Test merge
     */
    @Test
    public void whenTwoSortedArraysMergeToOneThenNewSortedArray() {
        ArrayMerge arrayMerge = new ArrayMerge();
        int[] firstArray = {1, 4, 5, 6, 11};
        int[] secondArray = {1, 3, 3, 8, 10};
        int[] result = arrayMerge.merge(firstArray, secondArray);
        int[] expected = {1, 1, 3, 3, 4, 5, 6, 8, 10, 11};
        assertThat(result, is(expected));
    }
}
