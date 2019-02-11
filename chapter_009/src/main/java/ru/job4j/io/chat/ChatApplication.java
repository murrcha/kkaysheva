package ru.job4j.io.chat;

import java.io.File;
import java.util.Objects;

/**
 * ChatApplication
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 02.2019
 */
public class ChatApplication {

    public static void main(String[] args) {
        String filePath;
        if (args.length == 0 || !new File(args[0]).exists()) {
            ClassLoader loader = ChatApplication.class.getClassLoader();
            filePath = Objects.requireNonNull(loader.getResource("phrases.txt")).getPath();
        } else {
            filePath = args[0];
        }
        AnswerSource source = new AnswerFileSource(filePath);
        ConsoleChat chat = new ConsoleChat(System.in, System.out, source);
        chat.chat();
    }
}
