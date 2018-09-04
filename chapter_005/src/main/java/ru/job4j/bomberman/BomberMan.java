package ru.job4j.bomberman;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * BomberMan
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class BomberMan extends Thread {

    /**
     * logger
     */
    private static final Logger LOG = LogManager.getLogger(BomberMan.class.getName());

    /**
     * constants of route
     */
    public static final int[] UP = {0, 1};
    public static final int[] DOWN = {0, -1};
    public static final int[] LEFT = {-1, 0};
    public static final int[] RIGHT = {1, 0};

    /**
     * Board
     */
    private final Board board;

    /**
     * Position
     */
    private Cell position;

    /**
     * Init
     */
    public BomberMan(Board board) {
        this.board = board;
    }

    /**
     * Method startGame init start position
     */
    public void startGame() {
        do {
            position = Cell.getNewPosition(Board.SIZE);
        } while (!board.startPosition(position));
    }

    /**
     * Method move
     * @param route for move
     * @return result
     */
    public boolean move(int[] route) throws IllegalArgumentException {
        boolean result = false;
        Cell destination = new Cell(
                position.getX() + route[0],
                position.getY() + route[1]);
        if (board.move(position, destination)) {
            position = destination;
            LOG.info(String.format("BomberMan position: %s %s",
                    destination.getX(),
                    destination.getY()));
            result = true;
        }
        return result;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void run() {
        startGame();
        while (!isInterrupted()) {
            try {
                if(!move(UP)) {
                    move(DOWN);
                }
                Thread.sleep(1000);
                if (!move(RIGHT)) {
                    move(LEFT);
                }
                Thread.sleep(1000);
                if (!move(DOWN)) {
                    move(UP);
                }
                Thread.sleep(1000);
                if (!move(LEFT)) {
                    move(RIGHT);
                }
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
