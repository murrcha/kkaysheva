package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * SimpleList - простой односвязный список
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleList<E> {

    /**
     * Размер списка
     */
    private int size;

    /**
     * Первый элемент в списке
     */
    private Node<E> first;

    /**
     * Method add - вставляет данные в начало списка
     * @param data
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Method delete - удаляет первый элемент в списке
     * @return удаленный элемент
     */
    public E delete() {
        Node<E> firstNode = this.first;
        if (firstNode == null) {
            throw new NoSuchElementException();
        }
        E element = firstNode.data;
        Node<E> nextLink = firstNode.next;
        firstNode.data = null;
        firstNode.next = null;
        this.first = nextLink;
        size--;
        return element;
    }

    /**
     * Method get - получает элемент по индексу
     * @param index
     * @return элемент
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Method getSize
     * @return размер списка
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Node - объект для хранения элемента в списке
     * @param <E>
     */
    private static class Node<E> {

        /**
         * Данные элемента
         */
        E data;

        /**
         * Ссылка на следующий элемент
         */
        Node<E> next;

        public Node(E data) {
            this.data = data;
        }
    }
}
