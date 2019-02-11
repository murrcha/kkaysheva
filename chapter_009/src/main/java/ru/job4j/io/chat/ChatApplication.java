package ru.job4j.io.chat;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * ChatApplication
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 02.2019
 */
public class ChatApplication {

    public static void main(String[] args) {
        URI fileUri = null;
        if (args.length == 0 || !new File(args[0]).exists()) {
            try {
                ClassLoader loader = ChatApplication.class.getClassLoader();
                fileUri = Objects.requireNonNull(loader.getResource("phrases.txt")).toURI();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            fileUri = URI.create(args[0]);
        }
        try {
            AnswerSource source = new AnswerFileSource(fileUri);
            ConsoleChat chat = new ConsoleChat(System.in, System.out, source);
            chat.chat();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
