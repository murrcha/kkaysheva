package ru.job4j.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * EmailNotification
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class EmailNotification {

    /**
     * Subject
     */
    private String subject;

    /**
     * Body
     */
    private String body;

    /**
     * Executor service
     */
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    /**
     * Method send
     * @param subject subject
     * @param body body
     * @param email email
     */
    private void send(String subject, String body, String email) {
        System.out.println(String.format("Send to %s, %s, %s", email, subject, body));
    }

    /**
     * Method emailTo add subject and body
     * @param user user
     */
    public void emailTo(User user) {
        pool.submit(() -> {
            subject = String.format("Notification %s to email %s", user.getName(), user.getEmail());
            body = String.format("Add new event to %s", user.getName());
            send(subject, body, user.getEmail());
        });
    }

    /**
     * Method close shutdown executor service
     */
    public void close() {
        this.pool.shutdown();
    }
}
