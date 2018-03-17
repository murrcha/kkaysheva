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
     * Method manWeight - идеальный вес для мужчины
     * @param height - рост
     * @return weight - идеальный вес
     */
    public double manWeight(double height) {
        return (height - 100) * 1.15;
    }

    /**
     * Method womanWeight - идеальный вес для женщины
     * @param height - рост
     * @return weight - идеальный вес
     */
    public double womanWeight(double height) {
        return (height - 110) * 1.15;
    }
}
