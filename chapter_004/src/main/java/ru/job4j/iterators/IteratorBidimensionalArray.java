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
    private int indexRows = 0;
    private int indexColumns = 0;

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
        return values.length > indexRows && values[indexRows].length > indexColumns;
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
        int result = values[indexRows][indexColumns];
        indexColumns++;
        if (indexColumns == values[indexRows].length) {
            indexRows++;
            indexColumns = 0;
        }
        return result;
    }
}
