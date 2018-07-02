package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleLinkedList
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleLinkedList<E> implements Iterable<E> {

    /**
     * Размер списка
     */
    private int size;

    /**
     * Счетчик изменений
     */
    private int modCount;

    /**
     * Ссылка на первый элемент
     */
    private Node<E> first;

    /**
     * Method checkIndex - проверяет вхождение индекса в границы размера списка
     * @param index
     */
    private void checkIndex(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException(String.format("Index: %s", index));
        }
    }

    /**
     * Method add - добавляет элемент в список
     * @param data
     */
    public void add(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.next = this.first;
        this.first = newNode;
        this.size++;
        this.modCount++;
    }

    /**
     * Method get - возвращает элемент по индексу
     * @param index
     * @return
     */
    public E get(int index) {
        checkIndex(index);
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int cursor = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return this.cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                E result = get(cursor);
                cursor++;
                return result;
            }
        };
    }

    /**
     * Объект для хранения элемента в списке
     * @param <E>
     */
    private static class Node<E> {
        E data;
        Node<E> next;
        public Node(E data) {
            this.data = data;
        }
    }
}
