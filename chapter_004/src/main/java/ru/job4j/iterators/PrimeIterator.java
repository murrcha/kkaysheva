package ru.job4j.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * PrimeIterator - итераор простых чисел
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class PrimeIterator implements Iterator {

    /**
     * Массив чисел для перебора
     */
    private final int[] values;

    /**
     * Индекс позиции каретки
     */
    private int indexPosition = 0;

    /**
     * Конструктор принимающий числа
     * @param values
     */
    public PrimeIterator(final int[] values) {
        this.values = values;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int index = this.indexPosition; index < this.values.length; index++) {
            if (this.isPrimeDigit(this.values[index])) {
                result = true;
                break;
            }
        }
        return this.values.length > this.indexPosition && result;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public Object next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        int result = this.values[this.indexPosition++];
        return this.isPrimeDigit(result) ? result : this.next();
    }

    /**
     * Method isPrimeDigit
     * @param digit
     * @return
     */
    private boolean isPrimeDigit(int digit) {
        boolean result = true;
        if (digit < 2) {
            result = false;
        } else {
            for (int index = 2; index < digit; index++) {
                if (digit % index == 0) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
