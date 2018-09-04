package ru.job4j.bomberman;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Monster
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Monster extends Thread {

    private static final Logger LOG = LogManager.getLogger(Monster.class.getName());

    /**
     * Board
     */
    private final Board board;

    /**
     * init board
     */
    public Monster(Board board) {
        this.board = board;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void run() {
        Cell position;
        do {
            position = Cell.getNewPosition(Board.SIZE);
        } while (!board.startPosition(position));
        while (!isInterrupted()) {
            Cell destination = Cell.getNewPosition(Board.SIZE);
            if (board.move(position, destination)) {
                position = destination;
                LOG.info(String.format("Monster position: %s %s",
                        destination.getX(),
                        destination.getY()));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
