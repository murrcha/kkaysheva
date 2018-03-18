package ru.job4j.calculator;

/**
 * Fit - расчет идеального веса
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Fit {

    /**
     * Константы используемы в формуле идеального веса
     */
    private static final int VALUE_FOR_MAN = 100;
    private static final int VALUE_FOR_WOMAN = 110;
    private static final double INDEX = 1.15;

    /**
     * Method manWeight - идеальный вес для мужчины
     * @param height - рост
     * @return weight - идеальный вес
     */
    public double manWeight(double height) {
        return (height - VALUE_FOR_MAN) * INDEX;
    }

    /**
     * Method womanWeight - идеальный вес для женщины
     * @param height - рост
     * @return weight - идеальный вес
     */
    public double womanWeight(double height) {
        return (height - VALUE_FOR_WOMAN) * INDEX;
    }
}
