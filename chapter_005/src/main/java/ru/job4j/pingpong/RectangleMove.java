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

    private static final int LIMIT_R = 290;
    private static final int LIMIT_L = 0;
    private static final int DURATION = 20;
    private static final int SHIFT_R = 1;
    private static final int SHIFT_L = -1;

    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void run() {
        int shift = SHIFT_R;
        while (!Thread.interrupted()) {
            if (this.rect.getX() == LIMIT_R) {
                shift = SHIFT_L;
            }
            if (this.rect.getX() == LIMIT_L) {
                shift = SHIFT_R;
            }
            this.rect.setX(this.rect.getX() + shift);
            try {
                Thread.sleep(DURATION);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
                return;
            }
        }
    }
}
