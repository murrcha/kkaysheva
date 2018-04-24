package ru.job4j.pseudo;

/**
 * Triangle - треугольник
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Triangle implements Shape {

    /**
     * Method draw - рисует треугольник
     * @return треугольник
     */
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("   +   ");
        pic.append("  +++  ");
        pic.append(" +++++ ");
        pic.append("+++++++");
        return pic.toString();
    }
}
