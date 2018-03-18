package ru.job4j.condition;

/**
 * DummyBot.
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class DummyBot {

    /**
     * Method answer - ответ на вопрос
     * @param question - вопрос
     * @return - ответ
     */
    public String answer(String question) {
        String answer = "Это ставит меня в тупик. Спросите другой вопрос.";
        if (question.equals("Привет, Бот")) {
            answer = "Привет, умник.";
        } else if (question.equals("Пока")) {
            answer = "До скорой встречи.";
        }
        return answer;
    }
}
