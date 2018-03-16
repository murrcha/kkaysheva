package ru.job4j.calculator;

/**
 * Calculator.
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Calculator {

    /**
     * result - результат вычисления
     */
    private double result;

    /**
     * Method getResult
     * @return result
     */
    public double getResult() {
        return result;
    }

    /**
     * Method add - сложение двух чисел
     * @param first - первое число
     * @param second - второе число
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Method subtract - вычитание второго числа из первого
     * @param first - первое число
     * @param second - второе число
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Method multiple - умножение двух чисел
     * @param first - первое число
     * @param second - второе число
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * Method div - деление первого числа на второе
     * @param first - первое число
     * @param second - второе число
     */
    public void div(double first, double second) {
        this.result = first / second;
    }
}
