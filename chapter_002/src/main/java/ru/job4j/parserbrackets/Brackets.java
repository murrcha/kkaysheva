package ru.job4j.parserbrackets;

/**
 * Brackets - скобки
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Brackets {

    /**
     * Открывающая скобка
     */
    private final String openingBracket;

    /**
     * Закрывающая скобка
     */
    private final String closingBracket;

    /**
     * Конструктор
     * @param openingBracket
     * @param closingBracket
     */
    public Brackets(String openingBracket, String closingBracket) {
        this.openingBracket = openingBracket;
        this.closingBracket = closingBracket;
    }

    /**
     * Method getOpeningBracket
     * @return
     */
    public String getOpeningBracket() {
        return this.openingBracket;
    }

    /**
     * Method getClosingBracket
     * @return
     */
    public String getClosingBracket() {
        return this.closingBracket;
    }

    /**
     * Method isPair
     * @param openingBracket
     * @param closingBracket
     * @return
     */
    public boolean isPair(String openingBracket, String closingBracket) {
        return this.openingBracket.equals(openingBracket) && this.closingBracket.equals(closingBracket);
    }

    /**
     * Method isOpeningBracket
     * @param bracket
     * @return
     */
    public boolean isOpeningBracket(String bracket) {
        return this.openingBracket.equals(bracket);
    }

    /**
     * Method isClosingBracket
     * @param bracket
     * @return
     */
    public boolean isClosingBracket(String bracket) {
        return this.closingBracket.equals(bracket);
    }
}
