package ru.job4j.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * ConvertMatrix2List Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ConvertMatrix2ListTest {

    /**
     * Test toList
     */
    @Test
    public void whenInputMatrix2x2ThenReturnListWithSize4() {
        ConvertMatrix2List converter = new ConvertMatrix2List();
        int[][] input = {{1, 2}, {3, 4}};
        List<Integer> expected = Arrays.asList(1, 2, 3, 4);
        List<Integer> result = converter.toList(input);
        assertThat(result, is(expected));
    }

    /**
     * Test toList
     */
    @Test
    public void whenInputEmptyMatrixThenReturnEmptyList() {
        ConvertMatrix2List converter = new ConvertMatrix2List();
        int[][] input = {};
        List<Integer> expected = Arrays.asList();
        List<Integer> result = converter.toList(input);
        assertThat(result, is(expected));
    }
}
