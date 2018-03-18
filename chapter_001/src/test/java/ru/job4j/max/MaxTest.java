package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Max Test
 *
 * @author Ksenya Kaysheva
 * @version $Id$
 * @since 0.1
 */
public class MaxTest {

    /**
     * Test maximum
     */
    @Test
    public void whenFirstLessSecond() {
        Max max = new Max();
        int result = max.maximum(1, 2);
        assertThat(result, is(2));
    }

    /**
     * Test maximum
     */
    @Test
    public void whenFirstMoreSecond() {
        Max max = new Max();
        int result = max.maximum(3, 2);
        assertThat(result, is(3));
    }

    /**
     * Test maximum
     */
    @Test
    public void whenFirstEquallySecond() {
        Max max = new Max();
        int result = max.maximum(5, 5);
        assertThat(result, is(5));
    }
}
