package ru.job4j.array;

/**
 * Matrix - вывод таблицы умножения
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Matrix {

    /**
     * Method multiple - возвращает заполненный массив заданного размера в виде таблицы умножения
     * @param size - размер
     * @return заполненный массив
     */
    public int[][] multiple(int size) {
        int[][] matrix = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                matrix[row][column] = (column + 1) * (row + 1);
            }
        }
        return matrix;
    }
}
