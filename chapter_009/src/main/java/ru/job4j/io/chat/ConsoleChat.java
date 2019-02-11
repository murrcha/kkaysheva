package ru.job4j.io.chat;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * ConsoleChat
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 02.2019
 */
public class ConsoleChat {

    private static final String STOP = "stop";
    private static final String CONTINUE = "continue";
    private static final String FINISH = "finish";
    private static final String WELCOME = "Welcome";
    private static final String EXIT = "Finished";

    private boolean mute = false;
    private boolean work = true;

    private final InputStream input;
    private final OutputStream output;
    private final AnswerSource source;

    public ConsoleChat(InputStream input, OutputStream output, AnswerSource source) {
        this.input = input;
        this.output = output;
        this.source = source;
    }

    public void chat() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(input, StandardCharsets.UTF_8));
             PrintStream writer = new PrintStream(output)
        ) {
            writer.println(WELCOME);
            do {
                switch (reader.readLine().toLowerCase()) {
                    case STOP:
                        mute = true;
                        break;
                    case CONTINUE:
                        mute = false;
                        break;
                    case FINISH:
                        work = false;
                        writer.println(EXIT);
                        break;
                    default:
                        if (!mute) {
                            writer.println(source.getAnswer());
                        }
                }
            } while (work);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
