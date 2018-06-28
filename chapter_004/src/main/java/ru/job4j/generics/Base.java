package ru.job4j.generics;

/**
 * Base
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public abstract class Base {

    /**
     * Идентификатор
     */
    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    /**
     * Method getId
     * @return идентификатор
     */
    public String getId() {
        return this.id;
    }
}
