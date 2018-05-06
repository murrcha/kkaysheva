package ru.job4j.chessboard;

/**
 * Cell - ячейка на шахманой доске
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Cell {
    /**
     * Координаты x и y
     */
    private int x;
    private int y;

    /**
     * Конструктор - инициализирует поля x и y
     * @param x
     * @param y
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method getX - возвращает координату x
     * @return
     */
    public int getX() {
        return this.x;
    }

    /**
     * Method getY - возвращает координату y
     * @return
     */
    public int getY() {
        return this.y;
    }
}
