package ru.job4j.piterservice;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * WordIndex Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class WordIndexTest {

    private static final String FILE = "pom.xml";
    private WordIndex index = new WordIndex();

    /**
     * Test find
     */
    @Test
    public void whenLoadFileThenTrieContainsWord() {
        try {
            index.loadFile(FILE);
        } catch (NoSuchFileException nsfe) {
            nsfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        assertThat(index.getTrie().searchWord("artifactid"), is(true));
        assertThat(index.getTrie().startsWithPrefix("art"), is(true));
        assertThat(index.getTrie().searchWord("fact"), is(false));
        assertThat(index.getTrie().startsWithPrefix("fact"), is(false));
        assertThat(index.getTrie().searchWord("pom"), is(true));
        assertThat(index.getTrie().searchWord("pomp"), is(false));
        assertThat(index.getTrie().searchWord("junior"), is(true));
        assertThat(index.getTrie().searchWord("juniors"), is(false));
        assertThat(index.getTrie().startsWithPrefix("jun"), is(true));
        assertThat(index.getTrie().searchWord("project"), is(true));
        assertThat(index.getTrie().searchWord("projects"), is(false));
        assertThat(index.getTrie().startsWithPrefix("pro"), is(true));
    }
}