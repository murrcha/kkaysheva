package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * FindLoop Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class FindLoopTest {

    /**
     * Test indexOf
     */
    @Test
    public void whenArrayHasElement5ThenIndex0() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 10, 3};
        int value = 5;
        int result = find.indexOf(input, value);
        int expected = 0;
        assertThat(result, is(expected));
    }

    /**
     * Test indexOf
     */
    @Test
    public void whenArrayHasNoElement7ThenIndexMinus1() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 10, 3};
        int value = 7;
        int result = find.indexOf(input, value);
        int expected = -1;
        assertThat(result, is(expected));
    }

    /**
     * Test indexOf
     */
    @Test
    public void whenArrayHasTwoElements5ThenIndex0() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 5, 10, 3};
        int value = 5;
        int result = find.indexOf(input, value);
        int expected = 0;
        assertThat(result, is(expected));
    }
}
