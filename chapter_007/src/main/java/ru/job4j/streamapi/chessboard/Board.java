package ru.job4j.streamapi.chessboard;

import ru.job4j.chessboard.Cell;
import ru.job4j.chessboard.Figure;
import ru.job4j.chessboard.ImpossibleMoveException;
import ru.job4j.chessboard.OccupiedWayException;
import ru.job4j.chessboard.FigureNotFoundException;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Board - шахматная доска (stream api)
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version 0.1
 * @since 03.11.2018
 */
public class Board {

    /**
     * Массив фигур на доске
     */
    private Figure[] figures = new Figure[32];

    private int count = 0;

    /**
     * Method getFigures - возвращает массив фигур на доске
     * @return figure array
     */
    public Figure[] getFigures() {
        return figures;
    }

    /**
     * Method add - добавляет фигуру
     * @param figure obj
     */
    public void add(Figure figure) {
        this.figures[count++] = figure;
    }

    /**
     * Method move - двигает фигуру на заданную позицию, если это возможно
     * @param source - исходная позиция
     * @param destination - конечная позиция
     * @return true или false
     * @throws ImpossibleMoveException - перемещение невозможно
     * @throws OccupiedWayException - заданный путь занят другими фигурами
     * @throws FigureNotFoundException - фигура в исходной позиции не найдена
     */
    public boolean move(Cell source, Cell destination) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean result = false;
        Figure figure = this.findFigureByCell(source);
        if (figure == null) {
            throw new FigureNotFoundException("На позиции нет фигуры");
        }
        for (Cell cell : figure.way(source, destination)) {
            if (this.findFigureByCell(cell) != null) {
                throw new OccupiedWayException("На пути есть другие фигуры");
            }
        }
        for (int index = 0; index < this.figures.length; index++) {
            if (figure.isOccupied(source)) {
                this.figures[index] = figure.copy(destination);
                result = true;
            }
        }
        return result;
    }

    /**
     * Method findFigureByCell - возвращает фигуру на указанной позиции, если она там есть
     * @param position позиция
     * @return фигуру или null
     */
    public Figure findFigureByCell(Cell position) {
        return Arrays.stream(this.figures)
                .filter(figure -> figure != null && figure.isOccupied(position))
                .findFirst()
                .orElse(null);
    }
}
