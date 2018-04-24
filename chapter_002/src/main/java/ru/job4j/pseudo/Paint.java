package ru.job4j.pseudo;

/**
 * Paint - объект для рисования
 *
 * @author Ksenya Kaysheba (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Paint {

    /**
     * Method draw - выводит фигуру в консоль
     * @param shape - фигура
     */
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }
}
