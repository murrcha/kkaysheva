package ru.job4j.array;

/**
 * FindLoop - поиск элемента в массиве путем перебора
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class FindLoop {

    /**
     * Method indexOf - возвращается индекс массива, если элемен найден
     * @param data - массив, в котором ищем
     * @param element - элемент, который ищем
     * @return индекс массива, если найден, иначе -1
     */
    public int indexOf(int[] data, int element) {
        int result = -1;
        for (int index = 0; index < data.length; index++) {
            if (data[index] == element) {
                result = index;
                break;
            }
        }
        return result;
    }
}
