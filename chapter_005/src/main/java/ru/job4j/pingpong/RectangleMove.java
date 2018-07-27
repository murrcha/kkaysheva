package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * RectangleMove
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class RectangleMove implements Runnable {

    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void run() {
        int limitRight = 290;
        int limitLeft = 0;
        int shift = 1;
        int duration = 20;
        while (true) {
            if (!Thread.interrupted()) {
                while (this.rect.getX() != limitRight) {
                    this.rect.setX(this.rect.getX() + shift);
                    try {
                        Thread.sleep(duration);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                        return;
                    }
                }
                while (this.rect.getX() != limitLeft) {
                    this.rect.setX(this.rect.getX() - shift);
                    try {
                        Thread.sleep(duration);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                        return;
                    }
                }
            } else {
                return;
            }
        }
    }
}
