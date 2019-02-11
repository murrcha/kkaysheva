package ru.job4j.io.chat;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * AnswerFileSource
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 02.2019
 */
public class AnswerFileSource implements AnswerSource {

    private final List<String> phrases = new ArrayList<>();
    private final Random random = new Random();

    public AnswerFileSource(URI fileUri) throws IOException {
        readPhraseFromFile(fileUri);
    }

    @Override
    public String getAnswer() {
        return phrases.get(random.nextInt(phrases.size()));
    }

    private void readPhraseFromFile(URI file) throws IOException {
        try (Stream<String> linesStream = Files.lines(Paths.get(file))) {
            linesStream.forEach(phrases::add);
        }
    }
}
