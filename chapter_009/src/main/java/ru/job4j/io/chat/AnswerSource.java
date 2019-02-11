package ru.job4j.io.chat;

import java.io.IOException;

/**
 * AnswerSource
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 02.2019
 */
public interface AnswerSource {

    String getAnswer() throws IOException;
}
