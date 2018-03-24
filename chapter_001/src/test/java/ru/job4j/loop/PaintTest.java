package ru.job4j.loop;

import org.junit.Test;
import java.util.StringJoiner;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Paint Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class PaintTest {

    /**
     * Test rightTrl
     */
    @Test
    public void whenHeight4ThenRightTriangle() {
        Paint paint = new Paint();
        String result = paint.rightTrl(4);
        assertThat(result,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add("^   ")
                        .add("^^  ")
                        .add("^^^ ")
                        .add("^^^^")
                        .toString()
                )
        );
    }

    /**
     * Test leftTrl
     */
    @Test
    public void whenHeight4ThenLeftTriangle() {
        Paint paint = new Paint();
        String result = paint.leftTrl(4);
        assertThat(result,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add("   ^")
                        .add("  ^^")
                        .add(" ^^^")
                        .add("^^^^")
                        .toString()
                )
        );
    }

    /**
     * Test pyramid
     */
    @Test
    public void whenHeight4ThenPyramid() {
        Paint paint = new Paint();
        String result = paint.pyramid(4);
        assertThat(result,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add("   ^   ")
                        .add("  ^^^  ")
                        .add(" ^^^^^ ")
                        .add("^^^^^^^")
                        .toString()
                )
        );
    }

    /**
     * Test pyramid
     */
    @Test
    public void whenHeight3ThenPyramid() {
        Paint paint = new Paint();
        String result = paint.pyramid(3);
        assertThat(result,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("  ^  ")
                                .add(" ^^^ ")
                                .add("^^^^^")
                                .toString()
                )
        );
    }
}
