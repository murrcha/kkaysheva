package ru.job4j.piterservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

/**
 * WordIndex
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class WordIndex {

    /**
     * length = 0;
     */
    private static final int LENGTH_ZERO = 0;

    /**
     * end of file
     */
    private static final int END_OF_FILE = -1;

    /**
     * prefix trie
     */
    private Trie trie = new Trie();

    /**
     * Method loadFile - read file and build prefix trie
     * @param fileName file
     * @throws IOException
     */
    public void loadFile(String fileName) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8);
        int ch;
        StringBuilder word = new StringBuilder();
        while ((ch = reader.read()) != END_OF_FILE) {
            if (Character.isLetter(ch)) {
                word.append((char) ch);
            } else if (!word.toString().isEmpty()){
                trie.insertWord(word.toString());
                word.setLength(LENGTH_ZERO);
            }
        }
    }

    /**
     * Method getTrie
     * @return trie
     */
    public Trie getTrie() {
        return trie;
    }
}
