package ru.job4j.tracker;

/**
 * Item - заявка
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Item {

    /**
     * Идентификатор заявки
     */
    private String id;

    /**
     * Имя заявки
     */
    private String name;

    /**
     * Описание заявки
     */
    private String description;

    /**
     * Дата/время создания
     */
    private long created;

    /**
     * Комментарии
     */
    private String[] comments;

    /**
     * Конструктор по умолчанию
     */
    public Item() {
    }

    /**
     * Конструктор - инициализирует поля
     * @param name - имя
     * @param description - описание
     */
    public Item(String name, String description, long created) {
        this.name = name;
        this.description = description;
        this.created = created;
    }

    /**
     * Method getId - получает идентификатор заявки
     * @return идентификатор заявки
     */
    public String getId() {
        return this.id;
    }

    /**
     * Method getName - получает имя заявки
     * @return имя заявки
     */
    public String getName() {
        return name;
    }

    /**
     * Method getDescription - получает описание заявки
     * @return описание заявки
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method getCreated - получает дату/время создания заявки
     * @return дата/время создания
     */
    public long getCreated() {
        return created;
    }

    /**
     * Method setId - устанавливает идентификатор заявке
     * @param id - идентификатор
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method - setName - устанавливает имя заявки
     * @param name - имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method setDescription - устанавливает описание заявки
     * @param description - описание
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
