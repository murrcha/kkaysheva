package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

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
@ThreadSafe
public class SimpleLinkedList<E> implements Iterable<E> {

    /**
     * Размер списка
     */
    @GuardedBy("this")
    private int size;

    /**
     * Счетчик изменений
     */
    @GuardedBy("this")
    private int modCount;

    /**
     * Ссылка на первый элемент
     */
    @GuardedBy("this")
    private Node<E> first;

    /**
     * Ссылка на последний элемент
     */
    @GuardedBy("this")
    private Node<E> last;

    /**
     * Method checkIndex - проверяет вхождение индекса в границы размера списка
     * @param index
     */
    private synchronized void checkIndex(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException(String.format("Index: %s", index));
        }
    }

    /**
     * Method getModCount
     * @return modCount
     */
    private synchronized int getModCount() {
        return this.modCount;
    }

    /**
     * Method add - добавляет элемент в список
     * @param data
     */
    public synchronized void add(E data) {
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
     * @return E data
     */
    public synchronized E get(int index) {
        checkIndex(index);
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Method deleteFirst - удаляет первый элемент из списка и возвращает его значение
     * @return E data
     */
    public synchronized E deleteFirst() {
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
     * @return E data
     */
    public synchronized E deleteLast() {
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
     * @return size
     */
    public synchronized int getSize() {
        return this.size;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int cursor = 0;
            final int expectedModCount = getModCount();

            /**
             * ${@inheritDoc}
             */
            @Override
            public boolean hasNext() {
                return this.cursor < getSize();
            }

            /**
             * ${@inheritDoc}
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (getModCount() != expectedModCount) {
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
