package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * ConvertMatrix2List - конвертер двухмерного массива в список
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ConvertMatrix2List {

    /**
     * Method toList - конвертирует двухмерный массив в список
     * @param array массив
     * @return список
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] rows : array) {
            for (int value : rows) {
                list.add(value);
            }
        }
        return list;
    }
}
