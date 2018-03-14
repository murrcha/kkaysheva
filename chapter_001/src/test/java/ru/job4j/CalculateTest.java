package ru.job4j;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * 
 * @author Ksenya Kaysheva (merrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class CalculateTest {

    /**
     * Test echo.
     */
    @Test
    public void whenTakeNameThenTreeEchoPlusName() {
        String input = "Ksenya Kaysheva";
        String expect = "Echo, echo, echo : Ksenya Kaysheva";
        Calculate calculate = new Calculate();
        String result = calculate.echo(input);
        assertThat(result, is(expect));
    }
}
