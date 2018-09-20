package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * FunctionCalculator Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class FunctionCalculatorTest {

    private final FunctionCalculator calculator = new FunctionCalculator();

    /**
     * Test linear function
     */
    @Test
    public void whenFunctionIsLinearThenReturnList() {
        int start = 1;
        int end = 10;
        List<Double> result = calculator.diapason(start, end, x -> 10 * x + 1);
        assertThat(result, is(Arrays.asList(11.0, 21.0, 31.0, 41.0, 51.0, 61.0, 71.0, 81.0, 91.0, 101.0)));
    }

    /**
     * Test quadratic function
     */
    @Test
    public void whenFunctionIsQuadraticThenReturnList() {
        int start = 1;
        int end = 10;
        List<Double> result = calculator.diapason(start, end, x -> Math.pow(x, 2));
        assertThat(result, is(Arrays.asList(1.0, 4.0, 9.0, 16.0, 25.0, 36.0, 49.0, 64.0, 81.0, 100.0)));
    }

    /**
     * Test logarithmic function
     */
    @Test
    public void whenFunctionIsLogarithmicThenReturnList() {
        int start = 1;
        int end = 3;
        List<Double> result = calculator.diapason(start, end, Math::log);
        assertThat(result.get(0), is(0.0));
        assertThat(result.get(1), is(closeTo(0.69, 0.004)));
        assertThat(result.get(2), is(closeTo(1.09, 0.009)));

    }
}