package ru.job4j.io.chat;

import java.io.*;

/**
 * AnswerFileSource
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 02.2019
 */
public class AnswerFileSource implements AnswerSource {

    private final String file;

    public AnswerFileSource(String file) {
        this.file = file;
    }

    @Override
    public String getAnswer() {
        String phrase = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            phrase = reader.lines().findAny().orElse("any");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrase;
    }
}
