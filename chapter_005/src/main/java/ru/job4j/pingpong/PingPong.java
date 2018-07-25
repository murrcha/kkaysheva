package ru.job4j.pingpong;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Group;

/**
 * PingPong
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class PingPong extends Application {

    private static final String JOB4J = "Ping Pong www.job4j.ru";

    /**
     * ${@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, 10, 10);
        group.getChildren().add(rect);
        new Thread(new RectangleMove(rect)).start();
        primaryStage.setScene(new Scene(group, limitX, limitY));
        primaryStage.setTitle(JOB4J);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
