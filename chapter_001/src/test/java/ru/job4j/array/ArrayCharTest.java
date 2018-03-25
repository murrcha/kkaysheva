package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * ArrayChar Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ArrayCharTest {

    /**
     * Test startWith
     */
    @Test
    public void whenStartWithPrefixTrue() {
        ArrayChar word = new  ArrayChar("Testing");
        boolean result = word.startWith("Test");
        assertThat(result, is(true));
    }

    /**
     * Test startWith
     */
    @Test
    public void whenStartWithPrefixFalse() {
        ArrayChar word = new  ArrayChar("Testing");
        boolean result = word.startWith("ing");
        assertThat(result, is(false));
    }
}
