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
public class EvenNumbersIterator implements Iterator<Integer> {

    /**
     * Значения для перебора
     */
    private final int[] values;

    /**
     * Позиция каретки
     */
    private int position = 0;

    /**
     * Конструктор принимающий значения для перебора
     * @param values
     */
    public EvenNumbersIterator(final int[] values) {
        this.values = values;
    }

    /**
     * Method isEven - проверят число на четность
     * @param digit
     * @return
     */
    private boolean isEven(int digit) {
        return digit % 2 == 0;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int index = position; index < values.length; index++) {
            if (isEven(values[index])) {
                result = true;
                break;
            }
        }
        return values.length > position && result;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        int result = values[position++];
        if (!isEven(result)) {
            result = this.next();
        }
        return result;
    }
}
