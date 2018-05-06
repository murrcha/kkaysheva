package ru.job4j.chessboard;

/**
 * OccupiedWayException - исключение в случае, когда путь занят другими фигурами
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class OccupiedWayException extends RuntimeException {

    /**
     * Конструктор - передает сообщение об ошибке в родительский класс
     * @param msg
     */
    public OccupiedWayException(String msg) {
        super(msg);
    }
}
