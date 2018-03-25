package ru.job4j.array;

/**
 * Square - заполнение массива числами от 1 до bound, возведенными в квадрат
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Square {

    /**
     * Method calculate - заполняет массив числами от 1 до bound, возведенными в квадрат
     * @param bound - размер массива
     * @return - заполненный массив
     */
    public int[] calculate(int bound) {
        int[] result = new int[bound];
        for (int i = 0; i < bound; i++) {
            result[i] = (int) Math.pow(i + 1, 2);
        }
        return result;
    }
}
