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
    private ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    /**
     * Method send
     * @param subject
     * @param body
     * @param email
     */
    private void send(String subject, String body, String email) {
        System.out.println(String.format("Send to %s", email));
    }

    /**
     * Method emailTo add subject and body
     * @param user
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

    /**
     * Method getSubject return subject
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Method body return body
     * @return body
     */
    public String getBody() {
        return body;
    }
}
