package ru.job4j.loop;

/**
 * Board
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Board {

    /**
     * Method paint - рисует шахматную доску из символов "х" и пробелов
     * @param width - ширина доски
     * @param height - высота доски
     * @return - строка в виде шахматной доски
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String line = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(line);
        }
        return screen.toString();
    }
}
