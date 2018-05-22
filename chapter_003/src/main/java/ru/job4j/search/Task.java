package ru.job4j.search;

/**
 * Task - задача
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Task {

    /**
     * Описание
     */
    private String description;

    /**
     * Приоритет
     */
    private int priority;

    /**
     * Constructor
     * @param description
     * @param priority
     */
    public Task(String description, int priority) {
        this.description = description;
        this.priority = priority;
    }

    /**
     * Method getDescription
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method getPriority
     * @return priority
     */
    public int getPriority() {
        return priority;
    }
}
