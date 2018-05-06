package ru.job4j.chessboard;

/**
 * FigureNotFoundException - исключение в случае, когда фигура не найдена в заданной позиции
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class FigureNotFoundException extends RuntimeException {

    /**
     * Конструктор - передает сообщение об ошибке в родительский класс
     * @param msg
     */
    public FigureNotFoundException(String msg) {
        super(msg);
    }
}
