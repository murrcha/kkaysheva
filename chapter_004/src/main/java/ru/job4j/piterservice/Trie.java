package ru.job4j.piterservice;

import java.util.HashMap;
import java.util.Map;

/**
 * Trie - prefix trie
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Trie {

    /**
     * root node trie
     */
    private TrieNode root = new TrieNode();

    /**
     * Method insertWord insert word in trie
     * @param word
     */
    public void insertWord(String word) {
        if (!word.isEmpty()) {
            TrieNode node = root;
            for (char ch : word.toLowerCase().toCharArray()) {
                node.children.putIfAbsent(ch, new TrieNode());
                node = node.children.get(ch);
            }
            node.isEnd = true;
        }
    }

    /**
     * Method searchWord search word in trie
     * @param word
     * @return
     */
    public boolean searchWord(String word) {
        TrieNode node = root;
        if (!word.isEmpty()) {
            for (char ch : word.toLowerCase().toCharArray()) {
                if (!node.children.containsKey(ch)) {
                    return false;
                }
                node = node.children.get(ch);
            }
        }
        return node.isEnd;
    }

    /**
     * Method startsWithPrefix search prefix in trie
     * @param prefix
     * @return
     */
    public boolean startsWithPrefix(String prefix) {
        if (prefix.isEmpty()) {
            return false;
        }
        TrieNode node = root;
        for (char ch : prefix.toLowerCase().toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return false;
            }
            node = node.children.get(ch);
        }
        return true;
    }

    /**
     * TrieNode node of trie
     */
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEnd = false;
    }
}
