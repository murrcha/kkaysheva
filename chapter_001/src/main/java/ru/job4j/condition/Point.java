package ru.job4j.condition;

/**
 * Point.
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Point {

    /**
     * Координаты точки х и y
     */
    private int x;
    private int y;

    /**
     * Конструктор - создание новой точки с определенными координатами
     * @param x - координата х
     * @param y - координата у
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method distanceTo - вычисляет расстояние до точки
     * @param that - точка
     * @return - расстояние
     */
    public double distanceTo(Point that) {
        return Math.sqrt(Math.pow(that.x - this.x, 2) + Math.pow(that.y - this.y, 2));
    }

    /**
     * Method main
     * @param args
     */
    public static void main(String[] args) {
        Point a = new Point(0, 1);
        Point b = new Point(2, 5);
        double result = a.distanceTo(b);
        System.out.println("Расстояние = " + result);
    }
}
