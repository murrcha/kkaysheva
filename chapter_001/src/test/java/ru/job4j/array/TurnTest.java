package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Turn Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class TurnTest {

    /**
     * Test back
     */
    @Test
    public void whenArrayWithLength5ThenTurnedArray() {
        Turn turner = new Turn();
        int[] input = new int[] {1, 2, 3, 4, 5};
        int[] result = turner.back(input);
        int[] expected = new int[] {5, 4, 3, 2, 1};
        assertThat(result, is(expected));
    }

    /**
     * Test back
     */
    @Test
    public void whenArrayWithLength6ThenTurnedArray() {
        Turn turner = new Turn();
        int[] input = new int[] {1, 2, 3, 4, 5, 6};
        int[] result = turner.back(input);
        int[] expected = new int[] {6, 5, 4, 3, 2, 1};
        assertThat(result, is(expected));
    }

    /**
     * Test back
     */
    @Test
    public void whenArrayWithLength0ThenTurnedArray() {
        Turn turner = new Turn();
        int[] input = new int[] {};
        int[] result = turner.back(input);
        int[] expected = new int[] {};
        assertThat(result, is(expected));
    }
}
