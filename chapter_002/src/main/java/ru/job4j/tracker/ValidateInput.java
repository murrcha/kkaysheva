package ru.job4j.tracker;

/**
 * ValidateInput - обрабатывает исключительные ситуации ввода пользователя
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ValidateInput implements Input {

    /**
     * Источник данных
     */
    private final Input input;

    /**
     * Конструктор - инициализирует поле input
     * @param input источник данных
     */
    public ValidateInput(Input input) {
        this.input = input;
    }

    /**
     * Method ask - возвращает выбор пользователя
     * @param question - запрос данных
     * @return выбор пользователя
     */
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    /**
     * Method ask обрабатывает исключения
     * @param question - запрос
     * @param range - диапазон номеров меню
     * @return номер меню или сообщение об ошибке
     */
    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Номер вне допустимого диапазона пунктов меню");
            } catch (NumberFormatException nfe) {
                System.out.println("Некорректный формат номера пункта меню");
            }
        } while (invalid);
        return value;
    }
}
