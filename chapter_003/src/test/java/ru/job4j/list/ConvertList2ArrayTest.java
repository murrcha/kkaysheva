package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * ConvertList2Array Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ConvertList2ArrayTest {

    /**
     * Test toArray
     */
    @Test
    public void whenConvert7ElementsThenReturnArray3x3() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 3);
        int[][] expected = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expected));
    }

    /**
     * Test toArray
     */
    @Test
    public void whenConvert8ElementsThenReturnArray2x4() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), 2);
        int[][] expected = {
                {1, 2, 3, 4},
                {5, 6, 7, 8}
        };
        assertThat(result, is(expected));
    }

    /**
     * Test toArray
     */
    @Test
    public void whenConvert0ElementsThenReturnArray0x0() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(Arrays.asList(), 1);
        int[][] expected = {{}};
        assertThat(result, is(expected));
    }

    /**
     * Test convert
     */
    @Test
    public void whenConvertListArrayThenReturnList() {
        ConvertList2Array converter = new ConvertList2Array();
        List<int[]> list = new ArrayList<>();
        list.add(new int[] {1, 2, 3});
        list.add(new int[] {4, 5, 6, 7});
        List<Integer> result = converter.convert(list);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        assertThat(result, is(expected));
    }

    /**
     * Test convert
     */
    @Test
    public void whenConvertEmptyListArrayThenReturnEmptyList() {
        ConvertList2Array converter = new ConvertList2Array();
        List<int[]> list = new ArrayList<>();
        list.add(new int[] {});
        list.add(new int[] {});
        List<Integer> result = converter.convert(list);
        List<Integer> expected = Arrays.asList();
        assertThat(result, is(expected));
    }
}
