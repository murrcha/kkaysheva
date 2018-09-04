package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;

/**
 * ArrayDuplicate Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ArrayDuplicateTest {

    /**
     * Test remove
     */
    @Test
    public void whenArrayWithDuplicatesThenRemoveIt() {
        String[] input = {"Cat", "Dog", "Cat", "Frog", "Frog", "Dog"};
        String[] expected = {"Cat", "Dog", "Frog"};
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] result = duplicate.remove(input);
        assertThat(result, arrayContainingInAnyOrder(expected));
    }
}
