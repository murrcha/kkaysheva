package ru.job4j.set;

import ru.job4j.list.SimpleLinkedList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleLinkedSet
 * @param <E>
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleListSet<E> implements Iterable<E> {

    /**
     * Контейнер
     */
    private SimpleLinkedList<E> set;

    /**
     * Счетчик изменений
     */
    int modCount;

    public SimpleListSet() {
        this.set = new SimpleLinkedList<>();
    }

    /**
     * Method add - добавляет уникальное значение в множество
     * @param value
     */
    public void add(E value) {
        boolean unique = true;
        if (this.set.getSize() > 0) {
            for (E element : this.set) {
                if (element != null && element.equals(value)) {
                    unique = false;
                }
            }
        }
        if (unique) {
            this.set.add(value);
            this.modCount++;
        }

    }

    /**
     * ${@inheritDoc}
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int cursor = 0;
            int expectedModCount = modCount;

            /**
             * ${@inheritDoc}
             * @return
             */
            @Override
            public boolean hasNext() {
                return this.cursor < set.getSize();
            }

            /**
             * ${@inheritDoc}
             * @return
             */
            @Override
            public E next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                E result = set.get(this.cursor);
                this.cursor++;
                return result;
            }
        };
    }
}
