package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Tracker - хранилище для заявок
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Tracker {

    /**
     * Объект для генерации случайного числа
     */
    private static final Random RN = new Random();

    /**
     * Список для хранения заявок
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Method add - добавляет новую заявку
     * @param item - заявка
     * @return новую заявку
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Method replace - заменяет заявку
     * @param id - идентификатор заявки
     * @param item - заявка
     */
    public void replace(String id, Item item) {
        for (int index = 0; index < this.items.size(); index++) {
            if (this.items.get(index).getId().equals(id)) {
                this.items.get(index).setName(item.getName());
                this.items.get(index).setDescription(item.getDescription());
                break;
            }
        }
    }

    /**
     * Method delete - удаляет заявку
     * @param id - ид заявки
     */
    public void delete(String id) {
        for (int index = 0; index < this.items.size(); index++) {
            if (this.items.get(index).getId().equals(id)) {
                items.remove(index);
                break;
            }
        }
    }

    /**
     * Method findAll - ищет все заявки
     * @return - массив всех заявок
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Method findByName - ищет заявки по имени
     * @param key - ключ для поиска
     * @return - массив соответствующих ключу заявок
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        for (Item item : this.items) {
            if (item.getName().contains(key)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Method findById - ищет заявку по идентификатору
     * @param id - идентификатор
     * @return - заявка
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Method generateId - генерирует идентификатор заявки
     * @return идентификатор заявки
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
}
