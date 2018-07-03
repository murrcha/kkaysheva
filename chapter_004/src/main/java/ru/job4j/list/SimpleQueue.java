package ru.job4j.list;

/**
 * SimpleQueue - простая очередь (FIFO)
 * @param <T>
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleQueue<T> extends SimpleLinkedList<T> {

    /**
     * Method poll - удаляет элемент в начале очереди и возвращает его значение
     * @return
     */
    public T poll() {
        return this.deleteFirst();
    }

    /**
     * Method push - добавляет элемент в конец очереди
     * @param value
     */
    public void push(T value) {
        this.add(value);
    }
}
