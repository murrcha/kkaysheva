package ru.job4j.loop;

/**
 * Factorial - вычисление факториала
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Factorial {

    /**
     * Method calc - вычисляет факториал числа n
     * @param n - число
     * @return факториал числа n
     */
    public int calc(int n) {
        int factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }
}
