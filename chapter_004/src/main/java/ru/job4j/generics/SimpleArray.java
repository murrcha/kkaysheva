package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleArray - простая обертка над массивом
 * @param <T>
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleArray<T> implements Iterable<T> {

    /**
     * Размер массива по умолчанию
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * Текущая позиция в массиве
     */
    private int position = 0;

    /**
     * Массив элементов
     */
    private Object[] elements;

    /**
     * Получает размер массива при создании
     * @param size
     */
    public SimpleArray(int size) {
        if (size > 0) {
            this.elements = new Object[size];
        } else if (size == 0) {
            this.elements = new Object[] {};
        } else {
            this.elements = new Object[DEFAULT_SIZE];
        }
    }

    /**
     * Method getPosition
     * @return текущую позицию в массиве
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Method getPosition
     * @return размер массива
     */
    public int getSize() {
        return this.elements.length;
    }

    /**
     * Method checkIndex - проверяет входит ли индекс в заданный диапазон (0..getPosition-1)
     * @param index
     */
    private void checkIndex(int index) {
        if (index >= this.elements.length || index < 0) {
            throw new IndexOutOfBoundsException(String.format("Index: %s", index));
        }
    }

    /**
     * Method add - добавляет новый элемент
     * @param model
     */
    public void add(T model) {
        checkIndex(this.position);
        this.elements[this.position++] = model;
    }

    /**
     * Method set - устанавливает новое значение по индексу
     * @param index
     * @param model
     */
    public void set(int index, T model) {
        checkIndex(index);
        this.elements[index] = model;
    }

    /**
     * Method delete - удаляет элемент по индексу
     * @param index
     */
    public void delete(int index) {
        checkIndex(index);
        int numMoved = this.elements.length - index - 1;
        if (numMoved > 0) {
            System.arraycopy(this.elements, index + 1, this.elements, index, numMoved);
        }
        this.position--;
    }

    /**
     * Method get - получает элемент по индексу
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) this.elements[index];
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < elements.length;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) elements[cursor++];
            }
        };
    }
}
