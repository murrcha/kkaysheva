package ru.job4j.io.archive;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * ArchiverTest
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 02.2019
 */
public class ArchiverTest {

    private static final String TEMP = System.getProperty("java.io.tmpdir");
    private static final String TEST_DIRECTORY = String.format("%s/test", TEMP);
    private static final String ZIP_FILE = "/archive.zip";
    private static final List<String> EXTS = Arrays.asList("java", "xml");

    @Before
    public void before() throws IOException {
        Path testDirectory = Paths.get(TEST_DIRECTORY);
        Files.createDirectory(testDirectory);
        Files.createTempFile(testDirectory, "test", ".java");
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @After
    public void after() {
        File testDir = new File(TEST_DIRECTORY);
        File[] files = testDir.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        testDir.delete();
    }

    @Test
    public void archiveDirectory() throws IOException {
        File directory = new File(TEST_DIRECTORY);
        File fileZip = new File(TEST_DIRECTORY + ZIP_FILE);
        Archiver archiver = new Archiver();
        archiver.zipArchive(directory, EXTS, fileZip);
        assertThat(fileZip.exists(), is(true));
    }
}