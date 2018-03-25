package ru.job4j.array;

/**
 * BuubleSort - сортировка массива методом перестановки (пузырьком)
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class BubbleSort {

    /**
     * Method sort - сортирует массив методом перестановки (пузырьком)
     *
     * @param array - массив
     * @return отсортированный массив
     */
    public int[] sort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
}
