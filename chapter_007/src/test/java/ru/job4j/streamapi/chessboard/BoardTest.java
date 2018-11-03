package ru.job4j.streamapi.chessboard;

import org.junit.Test;
import ru.job4j.chessboard.*;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class BoardTest {

    /**
     * Test add one figure
     */
    @Test
    public void whenAddFigureThenArrayContainThisFigure() {
        Board testBoard = new Board();
        Cell position = new Cell(1, 1);
        Bishop bishop = new Bishop(position);
        testBoard.add(bishop);
        assertThat(testBoard.getFigures()[0], instanceOf(Bishop.class));
        assertThat(testBoard.getFigures()[0].position.getX(), is(1));
        assertThat(testBoard.getFigures()[0].position.getY(), is(1));
    }

    /**
     * Test add two figure
     */
    @Test
    public void whenAddTwoFiguresThenArrayContainThereFigures() {
        Board testBoard = new Board();
        Cell firstPosition = new Cell(1, 1);
        Cell secondPosition = new Cell(3, 2);
        Bishop firstBishop = new Bishop(firstPosition);
        Bishop secondBishop = new Bishop(secondPosition);
        testBoard.add(firstBishop);
        testBoard.add(secondBishop);
        assertThat(testBoard.getFigures()[0], instanceOf(Bishop.class));
        assertThat(testBoard.getFigures()[1], instanceOf(Bishop.class));
        assertThat(testBoard.getFigures()[0].position.getX(), is(1));
        assertThat(testBoard.getFigures()[0].position.getY(), is(1));
        assertThat(testBoard.getFigures()[1].position.getX(), is(3));
        assertThat(testBoard.getFigures()[1].position.getY(), is(2));
    }

    /**
     * Test findFigureByCell
     */
    @Test
    public void whenFindFigureByCellThenReturnFigure() {
        Board testBoard = new Board();
        Cell position = new Cell(1, 3);
        Bishop bishop = new Bishop(position);
        testBoard.add(bishop);
        Figure figure = testBoard.findFigureByCell(position);
        assertThat(figure, instanceOf(Bishop.class));
        assertThat(figure.position.getX(), is(1));
        assertThat(figure.position.getY(), is(3));
    }

    /**
     * Test findFigureByCell
     */
    @Test
    public void whenFindFigureByEmptyCellThenReturnNull() {
        Board testBoard = new Board();
        Cell position = new Cell(1, 3);
        Bishop bishop = new Bishop(position);
        testBoard.add(bishop);
        Cell emptyPosition = new Cell(1, 1);
        assertThat(testBoard.findFigureByCell(emptyPosition), nullValue());
    }

    /**
     * Test valid move
     */
    @Test
    public void whenMoveFigureIsValidThenReturnTrue() {
        Board testBoard = new Board();
        Cell source = new Cell(1, 3);
        Bishop bishop = new Bishop(source);
        testBoard.add(bishop);
        Cell destination = new Cell(3, 1);
        assertThat(testBoard.move(source, destination), is(true));
        assertThat(testBoard.getFigures()[0].position.getX(), is(3));
        assertThat(testBoard.getFigures()[0].position.getY(), is(1));
    }

    /**
     * Test invalid move
     */
    @Test(expected = OccupiedWayException.class)
    public void whenMoveFigureOnOtherFigureThenReturnOccupiedWayException() {
        Board testBoard = new Board();
        Cell positionFirstBishop = new Cell(1, 1);
        Bishop firstBishop = new Bishop(positionFirstBishop);
        testBoard.add(firstBishop);
        Cell positionSecondBishop = new Cell(2, 2);
        Bishop secondBishop = new Bishop(positionSecondBishop);
        testBoard.add(secondBishop);
        testBoard.move(positionFirstBishop, positionSecondBishop);
    }

    /**
     * Test invalid move
     */
    @Test(expected = FigureNotFoundException.class)
    public void whenMoveEmptyFigureThenReturnFigureNotFoundException() {
        Board testBoard = new Board();
        Cell source = new Cell(1, 1);
        Cell destination = new Cell(2, 2);
        Cell positionBishop = new Cell(3, 3);
        Bishop bishop = new Bishop(positionBishop);
        testBoard.add(bishop);
        testBoard.move(source, destination);
    }
}