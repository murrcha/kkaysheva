package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleMap
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
@SuppressWarnings("unchecked")
public class SimpleMap<K, V> implements Iterable<SimpleMap.Entry<K, V>> {

    private static final int DEFAULT_SIZE = 16;

    private Entry<K, V>[] table;

    private int modCount;

    private int size;

    /**
     * Init table with default size
     */
    public SimpleMap() {
        this.table = new Entry[DEFAULT_SIZE];
    }

    /**
     * Init table with custom size or default size
     * @param size
     */
    public SimpleMap(int size) {
        if (size > 0) {
            this.table = new Entry[size];
        } else {
            this.table = new Entry[DEFAULT_SIZE];
        }
    }

    /**
     * Method growTable
     */
    private void growTable() {
        if (this.size >= this.table.length) {
            this.table = Arrays.copyOf(this.table, this.table.length * 2);
        }
    }

    /**
     * Method hash - get hash by key
     * @param key
     * @return
     */
    private int hash(K key) {
        int hash;
        if (key == null) {
            hash = 0;
        } else {
            hash = key.hashCode();
            hash = hash ^ (hash >>> 16);
        }
        return hash;
    }

    /**
     * Method getIndex -  get index by hash
     * @param hash
     * @return
     */
    private int getIndex(int hash) {
        return (this.table.length - 1) & hash;
    }

    /**
     * Method getSize
     * @return
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Method insert - insert new pair key-value
     * @param key
     * @param value
     * @return
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        int hash = hash(key);
        this.growTable();
        int index = getIndex(hash);
        if (this.table[index] == null) {
            Entry<K, V> newPair = new Entry<>(hash, key, value);
            this.table[index] = newPair;
            result = true;
            this.modCount++;
            this.size++;
        }
        return result;
    }

    /**
     * Method get - get value by key
     * @param key
     * @return
     */
    public V get(K key) {
        Entry<K, V> result = null;
        Entry<K, V> tempPair;
        int hash = hash(key);
        int index = getIndex(hash);
        if (this.table != null && this.table.length > 0 && this.table[index] != null) {
            tempPair = this.table[index];
            if (tempPair.hash == hash && tempPair.key == key || (key != null && key.equals(tempPair.key))) {
                result = tempPair;
            }
        }
        return result == null ? null : result.value;
    }

    /**
     * Method delete - delete pair by key
     * @param key
     * @return
     */
    public boolean delete(K key) {
        boolean result = false;
        Entry<K, V> tempPair;
        int hash = hash(key);
        int index = getIndex(hash);
        if (this.table != null && this.table.length > 0 && this.table[index] != null) {
            tempPair = this.table[index];
            if (tempPair.hash == hash && tempPair.key == key || (key != null && key.equals(tempPair.key))) {
                this.table[index] = null;
                result = true;
                this.modCount++;
                this.size--;
            }
        }
        return result;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public Iterator<SimpleMap.Entry<K, V>> iterator() {
        return new EntryIterator();
    }

    /**
     * EntryIterator - iterator for pair key-value
     */
    final class EntryIterator implements Iterator<SimpleMap.Entry<K, V>> {

        Entry<K, V> next;
        Entry<K, V> current;
        int cursor;
        int expectedModCount;

        public EntryIterator() {
            this.expectedModCount = modCount;
            this.current = null;
            this.next = null;
            this.cursor = 0;
            if (table != null && size > 0) {
                do {
                    this.next = table[cursor++];
                } while (this.cursor < table.length && this.next == null);
            }
        }

        /**
         * ${@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return this.next != null;
        }

        /**
         * ${@inheritDoc}
         */
        @Override
        public SimpleMap.Entry<K, V> next() {
            this.current = this.next;
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (this.expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            this.next = table[cursor++];
            if (this.next == null && table != null) {
                do {
                    this.next = table[cursor++];
                } while (this.cursor < table.length && this.next == null);
            }
            return this.current;
        }
    }

    /**
     * Entry - pair key-value
     * @param <K>
     * @param <V>
     */
    static class Entry<K, V> {
        private final int hash;
        private final K key;
        private V value;
        public Entry(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return this.key;
        }
    }
}
