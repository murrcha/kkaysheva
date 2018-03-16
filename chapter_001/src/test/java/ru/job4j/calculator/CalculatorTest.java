package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * CalculatorTest
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class CalculatorTest {

    /**
     * Test add
     */
    @Test
    public void when1Plus2Then3() {
        Calculator calculator = new Calculator();
        calculator.add(1D, 2D);
        double result = calculator.getResult();
        double expected = 3D;
        assertThat(result, is(expected));
    }

    /**
     * Test subtract
     */
    @Test
    public void when3Minus2Then1() {
        Calculator calculator = new Calculator();
        calculator.subtract(3D, 2D);
        double result = calculator.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }

    /**
     * Test multiple
     */
    @Test
    public void when9Multiple9Then81() {
        Calculator calculator = new Calculator();
        calculator.multiple(9D, 9D);
        double result = calculator.getResult();
        double expected = 81D;
        assertThat(result, is(expected));
    }

    /**
     * Test div
     */
    @Test
    public void when9Div3Then3() {
        Calculator calculator = new Calculator();
        calculator.div(9D, 3D);
        double result = calculator.getResult();
        double expected = 3D;
        assertThat(result, is(expected));
    }
}
