package ru.job4j.pseudo;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Triangle Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class TriangleTest {

    /**
     * Test draw
     */
    @Test
    public void whenDrawTriangleThenDrawTriangle() {
        Triangle testTriangle = new Triangle();
        assertThat(
                testTriangle.draw(),
                is(
                    new StringBuilder()
                        .append("   +   ")
                        .append("  +++  ")
                        .append(" +++++ ")
                        .append("+++++++")
                        .toString()
                )
        );
    }
}
