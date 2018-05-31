package ru.job4j.comparator;

import java.util.Comparator;

import static java.lang.Math.min;

/**
 * ListCompare - компаратор для строк
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ListCompare implements Comparator<String> {

    /**
     * ${@inheritDoc}
     */
    @Override
    public int compare(String left, String right) {
        int result = 0;
        int minLength = min(left.length(), right.length());
        for (int index = 0; index < minLength; index++) {
            char leftSymbol = left.charAt(index);
            char rightSymbol = right.charAt(index);
            if (leftSymbol != rightSymbol) {
                result = Character.compare(leftSymbol, rightSymbol);
                break;
            }
        }
        return result != 0 ? result : Integer.compare(left.length(), right.length());
    }
}
