package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

/**
 * FitTest.
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class FitTest {

    /**
     * Test manWeight
     */
    @Test
    public void manWeight() {
        Fit calculatorFit = new Fit();
        double weight = calculatorFit.manWeight(180);
        assertThat(weight, closeTo(92.0, 0.1));
    }

    /**
     * Test womanWeight
     */
    @Test
    public void womanWeight() {
        Fit calculatorFit = new Fit();
        double weight = calculatorFit.womanWeight(170);
        assertThat(weight, closeTo(69.0, 0.1));
    }
}
