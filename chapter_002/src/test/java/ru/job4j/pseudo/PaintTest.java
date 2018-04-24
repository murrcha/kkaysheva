package ru.job4j.pseudo;

import org.junit.After;
import org.junit.Before;
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
     * Дефолтный вывод в консоль
     */
    private final PrintStream stdOut = System.out;

    /**
     * Буфер для вывода результата
     */
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Включает вывод результата в буфер
     */
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(out));
    }

    /**
     * Возвращает дефолтный вывод в консоль
     */
    @After
    public void backOutput() {
        System.setOut(stdOut);
    }

    /**
     * Test draw square
     */
    @Test
    public void whenPaintDrawSquare() {
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
    }

    /**
     * Test draw triangle
     */
    @Test
    public void whenPaintDrawTriangle() {
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
    }
}
