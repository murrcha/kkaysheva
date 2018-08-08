package ru.job4j.parallelsearch;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * FinderFiles Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class FinderFilesTest {

    private static final String START_DIRECTORY = "./";
    private static final String PATTERN = "*.{java}";

    /**
     * Test finder files
     */
    @Test
    public void whenFinderFilesRunThenReturnFilesInList() {
        BlockingQueue<String> files = new LinkedBlockingQueue<>();
        FinderFiles finder = new FinderFiles(PATTERN, files);
        Path startPath = Paths.get(START_DIRECTORY);
        try {
            Files.walkFileTree(startPath, finder);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        assertThat(files.isEmpty(), is(false));
        System.out.println(files.size());
    }
}