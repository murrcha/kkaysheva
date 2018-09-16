package tracker;

/**
 * Input - интерфейс получения данных от пользователя
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public interface Input {

    /**
     * Method ask - запрашивает данные у пользователя
     * @param question - запрос данных
     * @return введенные данные пользователем
     */
    String ask(String question);

    /**
     * Method ask - запрашивает данные у пользователя
     * @param question - запрос данных
     * @param range диапазон возможных ответов
     * @return выбор пользователя
     */
    int ask(String question, int[] range);
}
