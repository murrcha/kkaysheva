package ru.job4j.tictactoe;

/**
 * Logic3T
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Logic3T {

    /**
     * table
     */
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    /**
     * Method isWinnerX
     * @return result
     */
    public boolean isWinnerX() {
        boolean result = false;
        for (int i = 0; i < table.length; i++) {
            if (table[i][0].hasMarkX()
                    && table[i][1].hasMarkX()
                    && table[i][2].hasMarkX()) {
                result = true;
                break;
            }
            if (table[0][i].hasMarkX()
                    && table[1][i].hasMarkX()
                    && table[2][i].hasMarkX()) {
                result = true;
                break;
            }
        }
        if (table[0][0].hasMarkX()
                && table[1][1].hasMarkX()
                && table[2][2].hasMarkX()) {
            result = true;
        }
        if (table[0][2].hasMarkX()
                && table[1][1].hasMarkX()
                && table[2][0].hasMarkX()) {
            result = true;
        }
        return result;
    }

    /**
     * Method isWinnerO
     * @return result
     */
    public boolean isWinnerO() {
        boolean result = false;
        for (int i = 0; i < table.length; i++) {
            if (table[i][0].hasMarkO()
                    && table[i][1].hasMarkO()
                    && table[i][2].hasMarkO()) {
                result = true;
                break;
            }
            if (table[0][i].hasMarkO()
                    && table[1][i].hasMarkO()
                    && table[2][i].hasMarkO()) {
                result = true;
                break;
            }
        }
        if (table[0][0].hasMarkO()
                && table[1][1].hasMarkO()
                && table[2][2].hasMarkO()) {
            result = true;
        }

        if (table[0][2].hasMarkO()
                && table[1][1].hasMarkO()
                && table[2][0].hasMarkO()) {
            result = true;
        }
        return result;
    }

    /**
     * Method hasGap
     * @return result
     */
    public boolean hasGap() {
        boolean result = true;
        int fullCell = 0;
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table.length; col++) {
                if (table[row][col].hasMarkX() || table[row][col].hasMarkO()) {
                    fullCell++;
                }
            }
        }
        if (fullCell == 9) {
            result = false;
        }
        return result;
    }
}
