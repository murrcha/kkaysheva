package ru.job4j.tracker;

/**
 * UserAction - определяет общие методы для всех событий
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public interface UserAction {

    /**
     * Method key - возвращает индекс меню (пункт меню)
     * @return индекс
     */
    int key();

    /**
     * Method execute - выполняет действие (добавление, редактирование, удаление заявки и т.д.)
     * @param input ввод
     * @param tracker хранилище
     */
    void execute(Input input, Tracker tracker);

    /**
     * Method info - выводит информацию о действии (пункт меню)
     * @return пункт меню
     */
    String info();
}
