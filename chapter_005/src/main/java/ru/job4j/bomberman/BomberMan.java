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

    private static final Logger LOG = LogManager.getLogger(BomberMan.class.getName());

    /**
     * Board
     */
    private final Board board;

    /**
     * Position
     */
    private Cell position;

    /**
     * Init start position
     */
    public BomberMan(Board board) {
        this.board = board;
        this.position = Cell.getNewPosition(board.getSize());
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void run() {
        board.startPosition(position);
        while (!isInterrupted()) {
            Cell destination = Cell.getNewPosition(board.getSize());
            if (board.move(position, destination)) {
                position = destination;
                LOG.info(String.format("New position: %s %s",
                        destination.getX(),
                        destination.getY()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }
}
