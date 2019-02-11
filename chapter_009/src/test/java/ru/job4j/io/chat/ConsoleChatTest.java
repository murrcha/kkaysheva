package ru.job4j.io.chat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * ConsoleChatTest
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 02.2019
 */
public class ConsoleChatTest {

    private InputStream input;
    private OutputStream output;
    private AnswerSource source;

    @Before
    public void before() {
        output = new ByteArrayOutputStream();
        source = new AnswerSourceStub();
    }

    @After
    public void after() throws IOException {
        input.close();
        output.close();
    }

    @Test
    public void chatAnswer() {
        String fraze = "Hello\nfinish";
        input = new ByteArrayInputStream(fraze.getBytes(StandardCharsets.UTF_8));
        ConsoleChat chat = new ConsoleChat(input, output, source);
        chat.chat();
        assertThat(output.toString(), is("Welcome\nbla bla bla\nFinished\n"));
    }

    @Test
    public void chatMute() throws IOException {
        String fraze = "stop\nhello\nfinish";
        input = new ByteArrayInputStream(fraze.getBytes(StandardCharsets.UTF_8));
        ConsoleChat chat = new ConsoleChat(input, output, source);
        chat.chat();
        assertThat(output.toString(), is("Welcome\nFinished\n"));
    }

    @Test
    public void chatMuteUnMute() {
        String fraze = "stop\nhello\ncontinue\nhello\nfinish";
        input = new ByteArrayInputStream(fraze.getBytes(StandardCharsets.UTF_8));
        ConsoleChat chat = new ConsoleChat(input, output, source);
        chat.chat();
        assertThat(output.toString(), is("Welcome\nbla bla bla\nFinished\n"));
    }

    @Test
    public void chatFinish() {
        String fraze = "finish";
        input = new ByteArrayInputStream(fraze.getBytes(StandardCharsets.UTF_8));
        ConsoleChat chat = new ConsoleChat(input, output, source);
        chat.chat();
        assertThat(output.toString(), is("Welcome\nFinished\n"));
    }
}