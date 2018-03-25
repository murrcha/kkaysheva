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
        int[][] matrix = new int[(size + 1)][(size + 1)];
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j <= size; j++) {
                if (i == 0 && j == 0) {
                    matrix [i][j] = 0;
                } else if (i == 0 && j != 0) {
                    matrix[i][j] = j;
                } else if (i != 0 && j == 0) {
                    matrix[i][j] = i;
                } else {
                    matrix[i][j] = i * j;
                }
            }
        }
        return matrix;
    }
}
