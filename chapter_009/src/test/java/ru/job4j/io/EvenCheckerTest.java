package ru.job4j.io;

import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * EvenCheckerTest
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 02.2019
 */
public class EvenCheckerTest {

    private InputStream input;

    @After
    public void after() throws IOException {
        input.close();
    }

    @Test
    public void isEvenNumberReturnTrue() {
        byte[] numbers = {1, 2, 3, 0};
        input = new ByteArrayInputStream(numbers);
        EvenChecker checker = new EvenChecker();
        assertThat(checker.isEvenNumber(input), is(true));
    }

    @Test
    public void isEvenNumberReturnFalse() {
        byte[] numbers = {1, 7, 3, 0};
        input = new ByteArrayInputStream(numbers);
        EvenChecker checker = new EvenChecker();
        assertThat(checker.isEvenNumber(input), is(false));
    }
}