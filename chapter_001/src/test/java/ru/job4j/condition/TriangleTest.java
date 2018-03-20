package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Triangle Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class TriangleTest {

    /**
     * Test area
     */
    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        double expected = 2D;
        assertThat(result, closeTo(expected, 0.1));
    }

    /**
     * Test area
     */
    @Test
    public void whenAreaSetTwoPointsThenNoTriangleArea() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 0);
        Point c = new Point(2, 0);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        double expected = -1D;
        assertThat(result, is(expected));
    }
}
