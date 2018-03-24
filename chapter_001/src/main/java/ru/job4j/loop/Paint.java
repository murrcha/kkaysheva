package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Paint - рисование пирамиды в псевдографике
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Paint {

    /**
     * Method pyramid - рисует пирамиду в псевдографике заданной высоты
     * @param height - высота пирамиды
     * @return - строка в форме пирамиды
     */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }

    /**
     * Method rightTrl - рисует правую половину пирамиды
     * @param height - высота пирамиды
     * @return - строка в форме правой половины пирамиды
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
                );
    }

    /**
     * Method leftTrl - рисует левую половину пирамиды
     * @param height - высота пирамиды
     * @return - строка в форме левой половины пирамиды
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }

    /**
     * Method loopBy - универсальный метод для рисования пирамиды в псевдографике
     * @param height - высота пирамиды
     * @param weight - ширина пирамиды
     * @param predict - условие для рисования заданной формы
     * @return - строка в заданной форме
     */
    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}
