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
     * Ссылка на последний элемент
     */
    private Node<E> last;

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
        final Node<E> last = this.last;
        final Node<E> newNode = new Node<>(last, data, null);
        this.last = newNode;
        if (last == null) {
            this.first = newNode;
        } else {
            last.next = newNode;
        }
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
     * Method deleteFirst - удаляет первый элемент из списка и возвращает его значение
     * @return
     */
    public E deleteFirst() {
        final Node<E> first = this.first;
        if (first == null) {
            throw new NoSuchElementException();
        }
        final E data = first.data;
        final Node<E> next = first.next;
        first.data = null;
        first.next = null;
        this.first = next;
        if (next == null) {
            this.last = null;
        } else {
            next.prev = null;
        }
        this.size--;
        this.modCount++;
        return data;
    }

    /**
     * Method deleteLast - удаляет последний элемент из списка и возвращает его
     * @return
     */
    public E deleteLast() {
        final Node<E> last = this.last;
        if (last == null) {
            throw new NoSuchElementException();
        }
        final E data = last.data;
        final Node<E> prev = last.prev;
        last.data = null;
        last.prev = null;
        this.last = prev;
        if (prev == null) {
            this.first = null;
        } else {
            prev.next = null;
        }
        this.size--;
        this.modCount++;
        return data;
    }

    /**
     * Method getSize
     * @return
     */
    public int getSize() {
        return this.size;
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
        Node<E> prev;
        public Node(Node<E> prev, E data, Node<E> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }
}
