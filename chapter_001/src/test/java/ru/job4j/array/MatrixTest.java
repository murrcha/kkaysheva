package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Matrix Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class MatrixTest {

    /**
     * Test multiple
     */
    @Test
    public void whenMatrixWithSize5ThenFullMatrix() {
        int size = 6;
        Matrix matrix = new Matrix();
        int[][] result = matrix.multiple(size);
        int[][] expected =
                {
                        {0, 1, 2, 3, 4, 5},
                        {1, 1, 2, 3, 4, 5},
                        {2, 2, 4, 6, 8, 10},
                        {3, 3, 6, 9, 12, 15},
                        {4, 4, 8, 12, 16, 20},
                        {5, 5, 10, 15, 20, 25}
                };
        assertThat(result, is(expected));
    }

    /**
     * Test multiple
     */
    @Test
    public void whenMatrixWithSize0ThenFullMatrix() {
        int size = 2;
        Matrix matrix = new Matrix();
        int[][] result = matrix.multiple(size);
        int[][] expected =
                {
                        {0, 1},
                        {1, 1}
                };
        assertThat(result, is(expected));
    }
}
