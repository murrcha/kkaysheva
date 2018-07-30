package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleArrayList - контейнер на базе массива
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class SimpleArrayList<E> implements Iterable<E> {

    /**
     * Размер по умолчанию
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * Контейнер для хранения
     */
    @GuardedBy("this")
    private Object[] container;

    /**
     * Текущая позиция
     */
    @GuardedBy("this")
    private int position;

    /**
     * Счетчик изменений
     */
    @GuardedBy("this")
    private int modCount;

    /**
     * Конструктор по умолчанию
     */
    public SimpleArrayList() {
        container = new Object[DEFAULT_SIZE];
    }

    /**
     * Конструктор с установкой размера
     * @param size
     */
    public SimpleArrayList(int size) {
        if (size > 0) {
            container = new Object[size];
        } else {
            container = new Object[DEFAULT_SIZE];
        }
    }

    /**
     * Method checkPosition - проверяет текущую позицию, если граница размера достигнута, то увеличиваем размер массива
     * @param position
     */
    private synchronized void checkPosition(int position) {
        if (position >= this.container.length) {
            this.container = Arrays.copyOf(this.container, this.position + DEFAULT_SIZE);
        }
    }

    /**
     * Method checkIndex - проверяет вхождение индекса в границы массива
     * @param index
     */
    private synchronized void checkIndex(int index) {
        if (index >= this.container.length || index < 0) {
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
     * Method getValue
     * @param cursor
     * @return E value
     */
    @SuppressWarnings("unchecked")
    private synchronized E getValue(int cursor) {
        return (E) container[cursor];
    }

    /**
     * Method getSize
     * @return container.length
     */
    public synchronized int getSize() {
        return this.container.length;
    }

    /**
     * Method getPosition
     * @return position
     */
    public synchronized int getPosition() {
        return this.position;
    }

    /**
     * Method add - добавляет новый элемент
     * @param value
     */
    public synchronized void add(E value) {
        checkPosition(this.position);
        this.container[position++] = value;
        modCount++;
    }

    /**
     * Method get - получает элемент по индексу
     * @param index
     * @return E value
     */
    @SuppressWarnings("unchecked")
    public synchronized E get(int index) {
        checkIndex(index);
        return (E) this.container[index];
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
                return cursor < getSize();
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
                return getValue(cursor++);
            }
        };
    }
}
