package ru.job4j.bomberman;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Board for bomber man
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class Board {

    private static final Logger LOG = LogManager.getLogger(Board.class.getName());

    /**
     * maximum values for blocks and monsters count
     */
    private static final int MAXIMUM_MONSTERS = 100;
    private static final int MAXIMUM_BLOCKS = 100;

    public static final int SIZE = 20;

    /**
     * board
     */
    @GuardedBy("this")
    private final ReentrantLock[][] board = new ReentrantLock[SIZE][SIZE];

    /**
     * monsters
     */
    private Monster[] monsters;

    /**
     * init board
     * @param monsters count
     * @param blocks count
     */
    public Board(int monsters, int blocks) {
        if (monsters < 0 || monsters > MAXIMUM_MONSTERS || blocks < 0 || blocks > MAXIMUM_BLOCKS) {
            throw new IllegalArgumentException("Illegal count monsters or blocks");
        }
        initBoard();
        initBlocks(blocks);
        startMonsters(monsters);
    }

    /**
     * Method initBoard
     */
    private void initBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = new ReentrantLock();
            }
        }
    }

    /**
     * Method startMonsters
     * @param monsters count
     */
    private void startMonsters(int monsters) {
        this.monsters = new Monster[monsters];
        for (int index = 0; index < monsters; index++) {
            this.monsters[index] = new Monster(this);
            this.monsters[index].start();
        }
    }

    /**
     * Method initBlocks
     * @param blocks count
     */
    private void initBlocks(int blocks) {
        for (int index = 0; index < blocks; index++) {
            Cell position = Cell.getNewPosition(SIZE);
            board[position.getX()][position.getY()].lock();
            LOG.info(String.format("Lock position: %s %s",
                    position.getX(),
                    position.getY()));
        }
    }

    /**
     * Method startPosition lock start position
     * @param position cell
     */
    public boolean startPosition(Cell position) {
        boolean result = false;
        if (board[position.getX()][position.getY()].tryLock()) {
            board[position.getX()][position.getY()].lock();
            result = true;
        }
        return result;
    }

    /**
     * Method move change position if is not locked
     * @param source current position
     * @param destination new position
     * @return is move or not
     */
    public boolean move(Cell source, Cell destination) {
        boolean result = false;
        try {
            if (board[destination.getX()][destination.getY()].tryLock(5, TimeUnit.SECONDS)) {
                board[destination.getX()][destination.getY()].lock();
                board[source.getX()][source.getY()].unlock();
                result = true;
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        return result;
    }

    /**
     * Method getSize
     * @return size board
     */
    public int getSize() {
        return this.SIZE;
    }

    /**
     * Method stopMonsters interrupt monsters threads
     */
    public void stopMonsters() {
        for (int index = 0; index < monsters.length; index++) {
            monsters[index].interrupt();
        }
    }
}
