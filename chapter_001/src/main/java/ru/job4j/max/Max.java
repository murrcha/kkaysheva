package ru.job4j.max;

/**
 * Max - максимум из двух чисел
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Max {

    /**
     * Method maximum - максимум из двух чисел
     * @param first - первое число
     * @param second - второе число
     * @return - максимум из двух чисел
     */
    public int maximum(int first, int second) {
        return first >= second ? first : second;
    }

    /**
     * Method maximum - максимум из трех чисел
     * @param first - первое число
     * @param second - второе число
     * @param third - третье число
     * @return - максимум из трех чисел
     */
    public int maximum(int first, int second, int third) {
        int temp = maximum(first, second);
        return maximum(temp, third);
    }
}
