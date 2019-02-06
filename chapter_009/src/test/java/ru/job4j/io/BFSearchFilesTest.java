package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * BFSearchFilesTest
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 02.2019
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class BFSearchFilesTest {

    private final String temp = String.format("%s/test", System.getProperty("java.io.tmpdir"));

    @Before
    public void before() throws IOException {
        File test = new File(temp);
        test.mkdir();
        File.createTempFile("test", ".txt", test);
        File.createTempFile("test", ".png", test);
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
    public void searchFilesInDirectory() {
        List<String> exts = Arrays.asList("txt", "csv");
        BFSearchFiles search = new BFSearchFiles();
        List<File> result = search.search(temp, exts);
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getName().endsWith("txt"), is(true));
    }

    @Test
    public void searchFakeFilesInDirectory() {
        List<String> exts = Arrays.asList("log", "csv");
        BFSearchFiles search = new BFSearchFiles();
        List<File> result = search.search(temp, exts);
        assertThat(result.isEmpty(), is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchFilesInNotDirectory() throws IOException {
        String path = temp + "/file.txt";
        List<String> exts = Arrays.asList("log", "csv");
        BFSearchFiles search = new BFSearchFiles();
        search.search(path, exts);
    }
}