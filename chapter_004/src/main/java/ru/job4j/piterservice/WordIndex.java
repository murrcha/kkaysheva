package ru.job4j.piterservice;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

/**
 * WordIndex
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class WordIndex {

    /**
     * index map
     */
    private Map<String, Set<Integer>> index = new HashMap<>();

    /**
     * Method loadFile read file and build index
     * @param filName file name
     * @throws IOException
     */
    public void loadFile(String filName) throws IOException {
        Scanner scanner = new Scanner(Paths.get(filName).toFile().getCanonicalFile());
        Pattern separators = Pattern.compile("%|\"|[0-9]|:|;|<|/|>|\\s|\\.|\\,|-|\\n|\\r|\\r\\n|\\]|\\[|\\{|\\}|\\)|\\(");
        scanner.useDelimiter(separators);
        Integer count = 0;
        while (scanner.hasNext()) {
            String word = scanner
                    .next()
                    .toLowerCase();
            if (!word.isEmpty()) {
                Set<Integer> entry;
                if (!index.containsKey(word)) {
                    entry = new HashSet<>();
                    entry.add(count);
                    index.put(word, entry);
                } else {
                    entry = index.get(word);
                    entry.add(count);
                    index.put(word, entry);
                }
                count++;
            }
        }
        System.out.println(this.index);
    }

    /**
     * Method getIndexes4Word get positions of word
     * @param searchWord word
     * @return set of positions
     */
    public Set<Integer> getIndexes4Word(String searchWord) {
        return this.index.getOrDefault(searchWord, null);
    }
}
