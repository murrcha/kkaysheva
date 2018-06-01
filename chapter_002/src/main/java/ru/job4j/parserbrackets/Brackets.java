package ru.job4j.parserbrackets;

/**
 * Brackets - скобки
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Brackets {

    private final String openingBracket;
    private final String closingBracket;

    public Brackets(String openingBracket, String closingBracket) {
        this.openingBracket = openingBracket;
        this.closingBracket = closingBracket;
    }

    public String getOpeningBracket() {
        return this.openingBracket;
    }

    public String getClosingBracket() {
        return this.closingBracket;
    }

    public boolean isPair(String openingBracket, String closingBracket) {
        return this.openingBracket.equals(openingBracket) && this.closingBracket.equals(closingBracket);
    }

    public boolean isOpeningBracket(String bracket) {
        return this.openingBracket.equals(bracket);
    }

    public boolean isClosingBracket(String bracket) {
        return this.closingBracket.equals(bracket);
    }
}
