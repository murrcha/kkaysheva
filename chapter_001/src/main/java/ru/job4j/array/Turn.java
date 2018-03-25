package ru.job4j.array;

/**
 * Turn - перевернуть массив задом наперед
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Turn {

    /**
     * Method back - переворачивает значения массива задом наперед
     * @param array - массив
     * @return перевернутый массив
     */
    public int[] back(int[] array) {
        for (int index = 0; index < array.length / 2; index++) {
            int temp = array[index];
            array[index] = array[array.length - index - 1];
            array[array.length - index - 1] = temp;
        }
        return array;
    }
}
