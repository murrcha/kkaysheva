package ru.job4j.search;

import java.util.LinkedList;

/**
 * PriorityQueue - очередь с приориетом
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class PriorityQueue {

    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Method put - добавляет задачу в соответствии с приоритетом
     * @param task задача
     */
    public void put(Task task) {
        if (tasks.isEmpty()) {
            tasks.add(task);
        } else {
            boolean added = false;
            for (int index = 0; index < this.tasks.size(); index++) {
                if (this.tasks.get(index).getPriority() > task.getPriority()) {
                    this.tasks.add(index, task);
                    added = true;
                    break;
                }
            }
            if (!added) {
                tasks.add(task);
            }
        }
    }

    /**
     * Method take - получает первую задачу
     * @return задача
     */
    public Task take() {
        return this.tasks.poll();
    }
}
