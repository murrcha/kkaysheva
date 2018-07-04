package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleArraySet - простое множество уникальных значений на базе массива
 * @param <E>
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleArraySet<E> implements Iterable<E> {

    /**
     * Контейнер
     */
    private SimpleArrayList<E> set;

    /**
     * Счетчик изменений
     */
    private int modCount;

    public SimpleArraySet() {
        set = new SimpleArrayList<>();
    }

    /**
     * Method add - добавляет уникальное значение в множество
     * @param value
     */
    public void add(E value) {
        boolean unique = true;
        if (set.getPosition() > 0) {
            for (E element : set) {
                if (element != null && element.equals(value)) {
                    unique = false;
                }
            }
        }
        if (unique) {
            set.add(value);
            modCount++;
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
                return this.cursor < set.getPosition();
            }

            /**
             * ${@inheritDoc}
             * @return
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                E result = set.get(cursor);
                cursor++;
                return result;
            }
        };
    }
}
