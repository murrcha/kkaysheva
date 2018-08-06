package ru.job4j.executorservice;

import org.junit.Test;

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

    private EmailNotification service = new EmailNotification();

    class Job implements Runnable {
        @Override
        public void run() {
            User user = new User("test", "test@mail.com");
            service.emailTo(user);
            service.send(service.getSubject(), service.getBody(), user.getEmail());
        }
    }

    /**
     * Test
     */
    @Test
    public void whenThen() {
        ExecutorService pool = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors());
        pool.submit(new Job());
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}