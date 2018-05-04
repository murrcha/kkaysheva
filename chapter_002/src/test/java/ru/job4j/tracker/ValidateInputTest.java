package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * ValidateInput Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ValidateInputTest {

    /**
     * Буфер для вывода результата в память
     */
    private final ByteArrayOutputStream memory = new ByteArrayOutputStream();

    /**
     * Дефолтный вывод в консоль
     */
    private final PrintStream out = System.out;

    /**
     * Включает вывод результата в память
     */
    @Before
    public void loadMemory() {
        System.setOut(new PrintStream(memory));
    }

    /**
     * Включает вывод результата в консоль
     */
    @After
    public void loadSystem() {
        System.setOut(this.out);
    }

    /**
     * Test ask
     */
    @Test
    public void whenInvalidFormatInputThenNumberFormatExeption() {
        ValidateInput testInput = new ValidateInput(
                new StubInput(new String[] {"invalid", "1"})
        );
        testInput.ask("Enter", new int[] {1});
        assertThat(this.memory.toString(), is(String.format("Некорректный формат номера пункта меню\n")));
    }

    /**
     * Test ask
     */
    @Test
    public void whenInvalidNumberInputThenMenuOutException() {
        ValidateInput testInput = new ValidateInput(
                new StubInput(new String[] {"-1", "1"})
        );
        testInput.ask("Enter", new int[] {1});
        assertThat(this.memory.toString(), is(String.format("Номер вне допустимого диапазона пунктов меню\n")));
    }
}
