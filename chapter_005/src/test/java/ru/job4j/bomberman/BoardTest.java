package ru.job4j.bomberman;

import org.junit.Test;

/**
 * Board Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class BoardTest {

    /**
     * Test bomber men start
     */
    @Test
    public void whenRunTwoBomberMenThenTheyMoveAroundTheBoard() {
        int sizeBoard = 20;
        Board board = new Board(sizeBoard);
        BomberMan bomberManOne = new BomberMan(board);
        BomberMan bomberManTwo = new BomberMan(board);
        bomberManOne.start();
        bomberManTwo.start();
        try {
            Thread.sleep(5000);
            bomberManOne.interrupt();
            bomberManTwo.interrupt();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}