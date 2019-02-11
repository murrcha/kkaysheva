package ru.job4j.io.chat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isOneOf;

/**
 * AnswerFileSourceTest
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 02.2019
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class AnswerFileSourceTest {

    private final String temp = String.format("%s/test", System.getProperty("java.io.tmpdir"));
    private File file;
    private File fakeFile;

    @Before
    public void before() throws IOException {
        File test = new File(temp);
        test.mkdir();
        file = new File(String.format("%s/phrases.txt", test));
        fakeFile = new File(String.format("%s/phrase.txt", test));
        file.createNewFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.append("phrase 1\n");
            writer.append("phrase 2\n");
            writer.append("phrase 3\n");
        }
    }

    @After
    public void after() {
        File testDir = new File(temp);
        File[] files = testDir.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        testDir.delete();
    }

    @Test
    public void getAnswerFromFile() throws IOException {
        AnswerFileSource source = new AnswerFileSource(file.toURI());
        assertThat(source.getAnswer(), isOneOf("phrase 1", "phrase 2", "phrase 3"));
    }

    @Test(expected = NoSuchFileException.class)
    public void getAnswerFromFileNotExists() throws IOException {
        new AnswerFileSource(fakeFile.toURI());
    }

    @Test(expected = NullPointerException.class)
    public void getAnswerFromNull() throws IOException {
        new AnswerFileSource(null);
    }
}