package ru.job4j.array;

import java.util.Arrays;

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
        for (int out = 0; out < matrix.length; out++) {
            matrix[0][out] = out;
            for (int in = 0; in < matrix.length; in++) {
                matrix[in][0] = in;
                matrix[out][in] = in * out;
            }
        }
        return matrix;
    }
}
