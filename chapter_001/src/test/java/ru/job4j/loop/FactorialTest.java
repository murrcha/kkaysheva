package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Factorial Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class FactorialTest {

    /**
     * Test calc
     */
    @Test
    public void whenN5ThenFactorial120() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(5);
        int expected = 120;
        assertThat(result, is(expected));
    }

    /**
     * Test calc
     */
    @Test
    public void whenN0ThenFactorial1() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(0);
        int expected = 1;
        assertThat(result, is(expected));
    }
}
