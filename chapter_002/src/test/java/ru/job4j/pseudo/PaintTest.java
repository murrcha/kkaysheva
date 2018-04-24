package ru.job4j.pseudo;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
     * Test draw square
     */
    @Test
    public void whenPaintDrawSquare() {
        PrintStream stdOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());
        assertThat(new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("++++++")
                                .append("++++++")
                                .append("++++++")
                                .append("++++++")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
        System.setOut(stdOut);
    }

    /**
     * Test draw triangle
     */
    @Test
    public void whenPaintDrawTriangle() {
        PrintStream stdOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Triangle());
        assertThat(new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("   +   ")
                                .append("  +++  ")
                                .append(" +++++ ")
                                .append("+++++++")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
        System.setOut(stdOut);
    }
}
