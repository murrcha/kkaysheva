package ru.job4j.list;

import java.util.Iterator;
import java.util.List;

/**
 * ConvertList2Array - конвертер списка в массив
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ConvertList2Array {

    /**
     * Method toArray конвертирует список в массив с заданным количеством строк
     * @param list список
     * @param rows количество строк
     * @return список
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() % rows == 0 ? list.size() / rows : list.size() / rows + 1;
        int[][] array = new int[rows][cells];
        Iterator<Integer> iterator = list.iterator();
        for (int row = 0; row < rows; row++) {
            for (int cell = 0; cell < cells; cell++) {
                if (iterator.hasNext()) {
                    array[row][cell] = iterator.next();
                } else {
                    array[row][cell] = 0;
                }
            }
        }
        return array;
    }
}
