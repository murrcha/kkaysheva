package ru.job4j.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * IteratorBidimensionalArray - итератор двухмерного массива
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class IteratorBidimensionalArray implements Iterator {

    /**
     * Возвращаемые значения
     */
    private final int[][] values;

    /**
     * Индексы строк и элементов в строке
     */
    private int row = 0;
    private int column = 0;

    /**
     * Конструктор принимающий значения для возврата
     * @param values
     */
    public IteratorBidimensionalArray(final int[][] values) {
        this.values = values;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return values.length > row && values[row].length > column;
    }

    /**
     * ${@inheritDoc}
     * @throws NoSuchElementException
     */
    @Override
    public Object next() throws NoSuchElementException {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        int result = values[row][column];
        column++;
        if (column == values[row].length) {
            row++;
            column = 0;
        }
        return result;
    }
}
