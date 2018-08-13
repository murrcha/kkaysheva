package ru.job4j.bomberman;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

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

    /**
     * board
     */
    @GuardedBy("this")
    private final ReentrantLock[][] board;

    /**
     * init board
     * @param size size
     */
    public Board(int size) {
        board = new ReentrantLock[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = new ReentrantLock();
            }
        }
    }

    /**
     * Method startPosition lock start position
     * @param position cell
     */
    public synchronized void startPosition(Cell position) {
        board[position.getX()][position.getY()].lock();
    }

    /**
     * Method move change position if is not locked
     * @param source current position
     * @param destination new position
     * @return is move or not
     */
    public synchronized boolean move(Cell source, Cell destination) {
        boolean result = false;
        if (board[destination.getX()][destination.getY()].tryLock()) {
            board[destination.getX()][destination.getY()].lock();
            board[source.getX()][source.getY()].unlock();
            result = true;
        }
        return result;
    }

    /**
     * Method getSize
     * @return size board
     */
    public synchronized int getSize() {
        return this.board.length;
    }
}
