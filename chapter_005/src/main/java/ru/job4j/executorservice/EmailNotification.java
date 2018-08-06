package ru.job4j.executorservice;

/**
 * EmailNotification
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class EmailNotification {

    /**
     * Subjectå
     */
    private String subject;

    /**
     * Body
     */
    private String body;

    /**
     * Method emailTo add subject and body
     * @param user
     */
    public void emailTo(User user) {
        this.subject = String.format("Notification %s to email %s", user.getName(), user.getEmail());
        this.body = String.format("Add new event to %s", user.getName());
    }

    /**
     * Method send
     * @param subject
     * @param body
     * @param email
     */
    public void send(String subject, String body, String email) {
        System.out.println(String.format("Send to %s", email));
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
