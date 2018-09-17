package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
        int end = 10;
        List<Double> result = calculator.diapason(start, end, Math::log);
        assertThat(result, is(Arrays.asList(0.0, 0.6931471805599453,
                1.0986122886681098, 1.3862943611198906,
                1.6094379124341003, 1.791759469228055,
                1.9459101490553132, 2.0794415416798357,
                2.1972245773362196, 2.302585092994046)));
    }
}