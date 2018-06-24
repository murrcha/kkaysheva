package ru.job4j.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Converter - конвертер итератора итераторов в итератор чисел
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Converter {

    /**
     * Method convert - конвертирует итератор итераторов чисел в итератор чисел
     * @param it - итератор итераторов чисел
     * @return итератор чисел
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            Iterator<Integer> iterator = it.next();

            /**
             * ${@inheritDoc}
             */
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            /**
             * ${@inheritDoc}
             */
            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int result = iterator.next();
                while (!iterator.hasNext() && it.hasNext()) {
                    iterator = it.next();
                }
                return result;
            }
        };
    }
}
