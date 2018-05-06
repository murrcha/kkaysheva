package ru.job4j.chessboard;

/**
 * ImpossibleMoveException - исключение в случае невозможности перемещения фигуры на заданную ячейку
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ImpossibleMoveException extends RuntimeException {

    /**
     * Конструктор - передает сообщение об ошибке в родительский класс
     * @param msg
     */
    public ImpossibleMoveException(String msg) {
        super(msg);
    }
}
