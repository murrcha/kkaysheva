package ru.job4j.chessboard;

/**
 * Board - шахматная доска
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Board {

    /**
     * Массив фигур на доске
     */
    private Figure[] figures = new Figure[32];

    private int count = 0;

    /**
     * Method getFigures - возвращает массив фигур на доске
     * @return
     */
    public Figure[] getFigures() {
        return figures;
    }

    /**
     * Method add - добавляет фигуру
     * @param figure
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
        Figure result = null;
        for (Figure figure : this.figures) {
            if (figure != null && figure.isOccupied(position)) {
                result = figure;
                break;
            }
        }
        return result;
    }
}
