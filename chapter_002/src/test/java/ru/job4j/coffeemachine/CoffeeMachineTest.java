package ru.job4j.coffeemachine;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * CoffeeMachine Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class CoffeeMachineTest {

    /**
     * Test changes price == value
     */
    @Test
    public void whenPriceEqualsValueThenReturnNull() {
        CoffeeMachine testMachine = new CoffeeMachine();
        int[] change = testMachine.changes(50, 50);
        assertThat(change, nullValue());
    }

    /**
     * Test changes price > value
     */
    @Test
    public void whenPriceMoreValueThenReturnNull() {
        CoffeeMachine testMachine = new CoffeeMachine();
        int[] change = testMachine.changes(55, 50);
        assertThat(change, nullValue());
    }

    /**
     * Test changes price < invalid value
     */
    @Test
    public void whenPriceLessInvalidValueThenReturnNull() {
        CoffeeMachine testMachine = new CoffeeMachine();
        int[] change = testMachine.changes(35, 51);
        assertThat(change, nullValue());
    }

    /**
     * Test changes price < value
     */
    @Test
    public void whenPrice35AndValue50ThenReturnChange15() {
        CoffeeMachine testMachine = new CoffeeMachine();
        int[] change = testMachine.changes(35, 50);
        assertThat(change[0], is(10));
        assertThat(change[1], is(5));
    }

    /**
     * Test changes price < value
     */
    @Test
    public void whenPrice47AndValue100ThenReturnChange53() {
        CoffeeMachine testMachine = new CoffeeMachine();
        int[] change = testMachine.changes(47, 100);
        assertThat(change[0], is(10));
        assertThat(change[1], is(10));
        assertThat(change[2], is(10));
        assertThat(change[3], is(10));
        assertThat(change[4], is(10));
        assertThat(change[5], is(2));
        assertThat(change[6], is(1));
    }
}
