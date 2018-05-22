package ru.job4j.search;

import org.junit.Test;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * PriorityQueueTest
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class PriorityQueueTest {

    /**
     * Test put and take
     */
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("Нормальный", 4));
        queue.put(new Task("Выше среднего", 2));
        queue.put(new Task("Высокий", 1));
        queue.put(new Task("Средний", 3));
        queue.put(new Task("Низкий", 5));
        Task result = queue.take();
        assertThat(result.getDescription(), is("Высокий"));
        assertThat(result.getPriority(), is(1));
        result = queue.take();
        assertThat(result.getDescription(), is("Выше среднего"));
        assertThat(result.getPriority(), is(2));
        result = queue.take();
        assertThat(result.getDescription(), is("Средний"));
        assertThat(result.getPriority(), is(3));
        result = queue.take();
        assertThat(result.getDescription(), is("Нормальный"));
        assertThat(result.getPriority(), is(4));
        result = queue.take();
        assertThat(result.getDescription(), is("Низкий"));
        assertThat(result.getPriority(), is(5));
        assertThat(queue.take(), nullValue());
    }
}
