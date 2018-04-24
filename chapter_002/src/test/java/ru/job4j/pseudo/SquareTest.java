package ru.job4j.pseudo;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Square Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SquareTest {

    /**
     * Test draw
     */
    @Test
    public void whenDrawSquareThenDrawSquare() {
        Square testSquare = new Square();
        assertThat(
                testSquare.draw(),
                is(
                    new StringBuilder()
                        .append("++++++")
                        .append("++++++")
                        .append("++++++")
                        .append("++++++")
                        .toString()
                )
        );
    }
}
