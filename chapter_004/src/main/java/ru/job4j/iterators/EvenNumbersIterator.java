package ru.job4j.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * EvenNumbersIterator - итератор четных чисел
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class EvenNumbersIterator implements Iterator {

    /**
     * Значения для перебора
     */
    private final int[] values;

    /**
     * Индекс позиции каретки
     */
    private int indexPosition = 0;

    /**
     * Конструктор принимающий значения для перебора
     * @param values
     */
    public EvenNumbersIterator(final int[] values) {
        this.values = values;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int index = indexPosition; index < values.length; index++) {
            if (values[index] % 2 == 0) {
                result = true;
                break;
            }
        }
        return values.length > indexPosition && result;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public Object next() throws NoSuchElementException {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        int result = values[indexPosition++];
        return result % 2 == 0 ? result : this.next();
    }
}
