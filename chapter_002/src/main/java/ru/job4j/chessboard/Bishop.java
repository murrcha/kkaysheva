package ru.job4j.chessboard;

import static java.lang.Math.abs;

/**
 * Bishop - реализация фигуры Слон
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Bishop extends Figure {

    /**
     * Конструктор - инициализация позиции
     * @param position
     */
    public Bishop(Cell position) {
        super(position);
    }

    /**
     * Method way - возвращает массив ячеек для движения в назначенную ячейку
     * @param source - откуда
     * @param destination - куда
     * @return
     */
    public Cell[] way(Cell source, Cell destination) throws ImpossibleMoveException {
        if (source.getX() == destination.getX()
                || source.getY() == destination.getY()
                || abs(destination.getX() - source.getX()) != abs(destination.getY() - source.getY())) {
            throw new ImpossibleMoveException("Движение фигуры по заданному пути невозможно");
        }
        Cell[] cells = new Cell[abs(destination.getX() - source.getX())];
        for (int index = 0; index < cells.length; index++) {
            int x = (destination.getX() > source.getX()) ? source.getX() + index + 1 : source.getX() - index - 1;
            int y = (destination.getY() > source.getY()) ? source.getY() + index + 1 : source.getY() - index - 1;
            cells[index] = new Cell(x, y);
        }
        return cells;
    }

    /**
     * Method copy - возвращает фигуру с новой позицией
     * @param destination - координата ячейки
     * @return
     */
    public Figure copy(Cell destination) {
        return new Bishop(destination);
    }
}
