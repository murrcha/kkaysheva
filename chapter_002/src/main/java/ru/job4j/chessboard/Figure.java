package ru.job4j.chessboard;

/**
 * Figure - абстрактный класс фигуры
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public abstract class Figure {

    /**
     * Позиция фигуры
     */
    protected final Cell position;

    /**
     * Конструктор - с инициализацией позиции
     * @param position
     */
    public Figure(Cell position) {
        this.position = position;
    }

    /**
     * Method way - возвращает массив ячеек, куда может пойти фигура
     * если невозможно, то возвращает исключение
     * @param source - откуда
     * @param destination - куда
     * @return массив ячеек
     * @throws ImpossibleMoveException
     */
    abstract Cell[] way(Cell source, Cell destination) throws ImpossibleMoveException;

    /**
     * Method copy - создает объект Figure с позицией
     * @param destination - позиция
     * @return объект Figure
     */
    abstract Figure copy(Cell destination);
}