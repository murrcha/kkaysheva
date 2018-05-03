package ru.job4j.tracker;

/**
 * ValidateInput - обрабатывает исключительные ситуации ввода пользователя
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ValidateInput extends ConsoleInput {

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
                value = super.ask(question, range);
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
