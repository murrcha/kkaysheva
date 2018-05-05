package ru.job4j.tracker;

/**
 * BaseAction - абстрактный класс для общих методов key и info
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public abstract class BaseAction implements UserAction {

    /**
     * Номер дейсвтия
     */
    private final int key;

    /**
     * Наименование действия
     */
    private final String name;

    /**
     * Конструктор - инициализирует поля key и name
     * @param key
     * @param name
     */
    protected BaseAction(int key, String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Method key - возвращает номер действия
     * @return номер
     */
    @Override
    public int key() {
        return this.key;
    }

    /**
     * Method info - возвращает строку действия с номером и наименованием
     * @return
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }
}
