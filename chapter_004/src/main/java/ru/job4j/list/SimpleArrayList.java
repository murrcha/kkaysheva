package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleArrayList - контейнер на базе массива
 *
 * @author Ksenya Kaysheva
 * @version $Id$
 * @since 0.1
 */
public class SimpleArrayList<E> implements Iterable<E> {

    /**
     * Размер по умолчанию
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * Контейнер для хранения
     */
    private Object[] container;

    /**
     * Текущая позиция
     */
    private int position;

    /**
     * Счетчик изменений
     */
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
        } else if (size <= 0) {
            container = new Object[DEFAULT_SIZE];
        }
    }

    /**
     * Method checkPosition - проверяет текущую позицию, если граница размера достигнута, то увеличиваем размер массива
     * @param position
     */
    private void checkPosition(int position) {
        if (position >= this.container.length) {
            this.container = Arrays.copyOf(this.container, this.position + DEFAULT_SIZE);
        }
    }

    /**
     * Method checkIndex - проверяет вхождение индекса в границы массива
     * @param index
     */
    private void checkIndex(int index) {
        if (index >= this.container.length || index < 0) {
            throw new IndexOutOfBoundsException(String.format("Index: %s", index));
        }
    }

    /**
     * Method getSize
     * @return
     */
    public int getSize() {
        return this.container.length;
    }

    /**
     * Method add - добавляет новый элемент
     * @param value
     */
    public void add(E value) {
        checkPosition(this.position);
        this.container[position++] = value;
        modCount++;
    }

    /**
     * Method get - получает элемент по индексу
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
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
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return cursor < container.length;
            }

            @Override
            @SuppressWarnings("unchecked")
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return (E) container[cursor++];
            }
        };
    }
}
