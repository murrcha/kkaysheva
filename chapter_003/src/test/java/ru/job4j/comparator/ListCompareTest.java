package ru.job4j.comparator;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

/**
 * ListCompare Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ListCompareTest {

    /**
     * Test compare
     */
    @Test
    public void whenStringsAreEqualThenZero() {
        ListCompare compare = new ListCompare();
        int result = compare.compare(
                "Иванов",
                "Иванов"
        );
        assertThat(result, is(0));
    }

    /**
     * Test compare
     */
    @Test
    public void whenLeftLessThanRightThenResultShouldBeNegative() {
        ListCompare compare = new ListCompare();
        int result = compare.compare(
                "Иванов",
                "Иванова"
        );
        assertThat(result, lessThan(0));
    }

    /**
     * Test compare
     */
    @Test
    public void whenLeftGreaterThanRightThenResultShouldBePositive() {
        ListCompare compare = new ListCompare();
        int result = compare.compare(
                "Петров",
                "Иванова"
        );
        assertThat(result, greaterThan(0));
    }

    /**
     * Test compare
     */
    @Test
    public void whenSecondCharOfLeftLessThanRightThenResultShouldBeNegative() {
        ListCompare compare = new ListCompare();
        int result = compare.compare(
                "Патрова",
                "Петров"
        );
        assertThat(result, lessThan(0));
    }

    /**
     * Test compare
     */
    @Test
    public void whenSecondCharOfLeftGreaterThanRightThenResultShouldBePositive() {
        ListCompare compare = new ListCompare();
        int result = compare.compare(
                "Петров",
                "Патров"
        );
        assertThat(result, greaterThan(0));
    }
}
