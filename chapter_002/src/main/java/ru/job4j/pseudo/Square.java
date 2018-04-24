package ru.job4j.pseudo;

/**
 * Square - квадрат
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Square implements Shape {

    /**
     * Method draw - рисует квадрат
     * @return квадрат
     */
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("++++++");
        pic.append("++++++");
        pic.append("++++++");
        pic.append("++++++");
        return pic.toString();
    }
}
