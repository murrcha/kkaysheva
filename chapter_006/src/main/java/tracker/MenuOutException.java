package tracker;

/**
 * MenuOutException - исключение используемое в меню
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class MenuOutException extends RuntimeException {

    /**
     * Конструктор - передает сообщение об ошибке в родительский класс
     * @param msg
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}
