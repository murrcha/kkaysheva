package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * DummyBot Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class DummyBotTest {

    /**
     * Test answer
     */
    @Test
    public void whenGreetBot() {
        DummyBot bot = new DummyBot();
        assertThat(bot.answer("Привет, Бот"),
                is("Привет, умник."));
    }

    /**
     * Test answer
     */
    @Test
    public void whenBuyBot() {
        DummyBot bot = new DummyBot();
        assertThat(bot.answer("Пока"),
                is("До скорой встречи."));
    }

    /**
     * Test answer
     */
    @Test
    public void whenUnknownBot() {
        DummyBot bot = new DummyBot();
        assertThat(bot.answer("Как тебя зовут?"),
                is("Это ставит меня в тупик. Спросите другой вопрос."));
    }
}
