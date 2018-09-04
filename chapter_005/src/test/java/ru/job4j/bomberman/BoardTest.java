package ru.job4j.bomberman;

import org.junit.Ignore;
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
    @Ignore
    @Test
    public void whenRunGameWithMonstersAndBomberManMoveThen() {
        int monsters = 10;
        int blocks = 50;
        Board board = new Board(monsters, blocks);
        BomberMan bomberMan = new BomberMan(board);
        bomberMan.start();
        try {
            Thread.sleep(3000);

        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        bomberMan.interrupt();
        board.stopMonsters();
    }
}