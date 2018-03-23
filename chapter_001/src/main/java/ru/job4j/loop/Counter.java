package ru.job4j.loop;

/**
 * Counter - вычисление суммы чисел
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Counter {

    /**
     * Method add - вычисляет сумму четных чисел в диапазоне
     * @param start - значение начала диапазона
     * @param finish - значение конца диапазона
     * @return - сумма четных чисел
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
