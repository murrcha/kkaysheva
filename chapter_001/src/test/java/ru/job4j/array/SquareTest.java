package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Square Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SquareTest {

    /**
     * Test calculate
     */
    @Test
    public void whenBound3Then149() {
        int bound = 3;
        Square square = new Square();
        int[] result = square.calculate(bound);
        int[] expected = new int[] {1, 4, 9};
        assertThat(result, is(expected));
    }

    /**
     * Test calculate
     */
    @Test
    public void whenBound0Then0() {
        int bound = 0;
        Square square = new Square();
        int[] result = square.calculate(bound);
        int[] expected = new int[] {};
        assertThat(result, is(expected));
    }

    /**
     * Test calculate
     */
    @Test
    public void whenBound1Then1() {
        int bound = 1;
        Square square = new Square();
        int[] result = square.calculate(bound);
        int[] expected = new int[] {1};
        assertThat(result, is(expected));
    }
}
