package ru.job4j.list;

/**
 * SimpleStack - простой стэк (LIFO)
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleStack<T> extends SimpleLinkedList<T> {

    /**
     * Method poll - удаляет элемент на вершине стэка и возвращает его значение
     * @return
     */
    public T poll() {
        return this.deleteLast();
    }

    /**
     * Method push - добавляет элемент на вершину стэка
     * @param value
     */
    public void push(T value) {
        this.add(value);
    }
}
