package ru.job4j.chessboard;

import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Bishop Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class BishopTest {

    /**
     * Test valid way
     */
    @Test
    public void whenValidWayThenReturnCellsArray() {
        Cell position = new Cell(6, 1);
        Bishop testBishop = new Bishop(position);
        Cell destination = new Cell(3, 4);
        Cell[] resultCells = testBishop.way(position, destination);
        assertThat(resultCells[0].getX(), is(5));
        assertThat(resultCells[0].getY(), is(2));
        assertThat(resultCells[1].getX(), is(4));
        assertThat(resultCells[1].getY(), is(3));
        assertThat(resultCells[2].getX(), is(3));
        assertThat(resultCells[2].getY(), is(4));
    }

    /**
     * Test invalid way
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenInvalidWayThenReturnImpossibleWayException() {
        Cell position = new Cell(6, 1);
        Bishop testBishop = new Bishop(position);
        Cell destination = new Cell(5, 6);
        testBishop.way(position, destination);
    }

    /**
     * Test copy
     */
    @Test
    public void whenCopyFigureBishopThenReturnNewBishop() {
        Cell position = new Cell(1, 1);
        Cell destination = new Cell(2, 2);
        Bishop testBishop = new Bishop(position);
        assertThat(testBishop.copy(destination), instanceOf(Bishop.class));
        assertThat(testBishop.copy(destination).position.getX(), is(2));
        assertThat(testBishop.copy(destination).position.getY(), is(2));
    }

    /**
     * Test isOccupied
     */
    @Test
    public void whenFigureIsOccupiedThenReturnTrue() {
        Cell position = new Cell(1, 1);
        Cell checkPosition = new Cell(1, 1);
        Bishop testBishop = new Bishop(position);
        assertThat(testBishop.isOccupied(checkPosition), is(true));
    }

    /**
     * Test isOccupied
     */
    @Test
    public void whenFigureNotIsOccupiedThenReturnFalse() {
        Cell position = new Cell(1, 1);
        Cell checkPosition = new Cell(2, 2);
        Bishop testBishop = new Bishop(position);
        assertThat(testBishop.isOccupied(checkPosition), is(false));
    }
}
