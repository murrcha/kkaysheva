package ru.job4j.bomberman;

import java.util.Random;

/**
 * Cell
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Cell {

    /**
     * x, y coordinates
     */
    final private int x;
    final private int y;

    /**
     * Init cell
     * @param x x
     * @param y y
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method getX
     * @return x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Method getY
     * @return y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Method getNewPosition get new random cell
     * @param bound size board
     * @return cell
     */
    public static Cell getNewPosition(int bound) {
        return new Cell(new Random().nextInt(bound), new Random().nextInt(bound));
    }
}
