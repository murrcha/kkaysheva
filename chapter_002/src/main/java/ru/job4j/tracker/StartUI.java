package ru.job4j.tracker;

/**
 * StartUI - точка входа в программу
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class StartUI {

    /**
     * Интерфейс для получения данных от пользователя
     */
    private final Input input;
    /**
     * Хранилище заявок
     */
    private final Tracker tracker;

    /**
     * Конструктор - инициализирует поля
     * @param input - ввод данных
     * @param tracker - хранилище заявок
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Method init - реализует основной цикл программы
     */
    public void init() {
        MenuTracker menu = new  MenuTracker(this.input, this.tracker);
        menu.fillActions();
        int key;
        do {
            menu.show();
            key = input.ask("Введите пункт меню: ", menu.getRange());
            menu.select(key);
        } while (key != 6);
    }

    /**
     * Method main - запуск программы
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(
                new ValidateInput(
                        new ConsoleInput()),
                new Tracker()
        ).init();
    }
}
