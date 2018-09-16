package tracker;

import java.sql.Timestamp;

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
    private int id;

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
    private Timestamp created;

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
    public Item(String name, String description, Timestamp created) {
        this.name = name;
        this.description = description;
        this.created = created;
    }

    /**
     * Конструктор - инициализирует поля
     * @param name - имя
     * @param description - описание
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Method getId - получает идентификатор заявки
     * @return идентификатор заявки
     */
    public int getId() {
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
    public Timestamp getCreated() {
        return created;
    }

    /**
     * Method setId - устанавливает идентификатор заявке
     * @param id - идентификатор
     */
    public void setId(int id) {
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

    /**
     * Method setCreated - set create date
     * @param createDate date
     */
    public void setCreated(Timestamp createDate) {
        this.created = createDate;
    }
}
