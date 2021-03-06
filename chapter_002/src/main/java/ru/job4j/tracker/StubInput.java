package ru.job4j.tracker;

/**
 * StubInput - эмуляция выбора пользователя
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class StubInput implements Input {

    /**
     * Последовательность ответов пользователя
     */
    private String[] answers;

    /**
     * Количество вызовов метода ask
     */
    private int position = 0;

    /**
     * Конструктор - инициализация answers
     * @param answers
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Method ask - возвращает выбор пользователя
     * @param question - вопрос
     * @return выбор пользователя
     */
    @Override
    public String ask(String question) {
        return this.answers[this.position++];
    }

    /**
     * Method ask - возвращает выбор пользователя
     * @param question - вопрос
     * @param range - диапазон
     * @return выбор пользователя
     */
    @Override
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Номер пункта меню вне диапазона");
        }
        return key;
    }
}
