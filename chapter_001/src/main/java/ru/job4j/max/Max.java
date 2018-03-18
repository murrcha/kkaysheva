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
     * Method max - максимум из двух чисел
     * @param first - первое число
     * @param second - второе число
     * @return - максимум из двух чисел
     */
    public int maximum(int first, int second) {
        return first >= second ? first : second;
    }
}
