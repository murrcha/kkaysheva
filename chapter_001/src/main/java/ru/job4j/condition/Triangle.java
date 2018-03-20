package ru.job4j.condition;

/**
 * Triangle - вычисление площади треугольника
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Triangle {

    /**
     * Константа для формулы полупериметра
     */
    private static final int VALUE_PERIOD = 2;

    /**
     * Вершины треугольника
     */
    private Point a;
    private Point b;
    private Point c;

    /**
     * Конструктор - создание треугольника с тремя вершинами
     * @param a - вершина А
     * @param b - вершина В
     * @param c - вершина С
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Method period - вычисляет полупериметра треугольника по длинам сторон
     * @param ab - длина стороны АВ
     * @param ac - длина стороны АС
     * @param bc - длина стороны ВС
     * @return - значение полупериметра
     */
    private double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / VALUE_PERIOD;
    }

    /**
     * Method exist - определяет существование треугольника по длинам сторон
     * @param ab - длина стороны АВ
     * @param ac - длина стороны АС
     * @param bc - длина стороны ВС
     * @return
     */
    private boolean exist(double ab, double ac, double bc) {
        return (ab != 0 && ac != 0 && bc != 0);
    }

    /**
     * Method area - вычисляет площадь треугольника
     * @return - значение площади, если треугольник существует, иначе -1
     */
    public double area() {
        double result = -1D;
        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(this.c);
        double period = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            result = Math.sqrt(period * (period - ab) * (period - ac) * (period - bc));
        }
        return result;
    }
}
