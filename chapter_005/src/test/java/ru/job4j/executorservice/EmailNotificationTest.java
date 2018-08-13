package ru.job4j.executorservice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * EmailNotification Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class EmailNotificationTest {

    /**
     * Test
     */
    @Test
    public void whenSendEmailWithExecutorServiceThenTaskIsDone() {
        EmailNotification service = new EmailNotification();
        List<User> users = new ArrayList<>();
        for (int index = 0; index < 100; index++) {
            users.add(new User(String.format("Name-%s", index), String.format("user-%s@mail.com", index)));
        }
        for (User user : users) {
            service.emailTo(user);
        }
        service.close();
    }
}