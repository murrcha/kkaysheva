package ru.job4j.tictactoe;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Logic3T Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Logic3TTest {

    @Test
    public void whenHasXWinner() {
        Figure3T[][] table = {
                {new Figure3T(true), new Figure3T(), new Figure3T()},
                {new Figure3T(), new Figure3T(true), new Figure3T()},
                {new Figure3T(), new Figure3T(), new Figure3T(true)},
        };
        Logic3T logic = new Logic3T(table);
        assertThat(logic.isWinnerX(), is(true));
    }

    @Test
    public void whenHasOWinner() {
        Figure3T[][] table = {
                {new Figure3T(false), new Figure3T(), new Figure3T()},
                {new Figure3T(false), new Figure3T(true), new Figure3T()},
                {new Figure3T(false), new Figure3T(), new Figure3T(true)},
        };
        Logic3T logic = new Logic3T(table);
        assertThat(logic.isWinnerO(), is(true));
    }

    @Test
    public void whenHasGas() {
        Figure3T[][] table = {
                {new Figure3T(true), new Figure3T(), new Figure3T()},
                {new Figure3T(), new Figure3T(true), new Figure3T()},
                {new Figure3T(), new Figure3T(), new Figure3T(true)},
        };
        Logic3T logic = new Logic3T(table);
        assertThat(logic.hasGap(), is(true));
    }
}