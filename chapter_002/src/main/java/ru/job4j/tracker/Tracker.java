package ru.job4j.tracker;

import java.util.Arrays;
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
     * Массив для хранения заявок
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки
     */
    private int position = 0;

    /**
     * Method add - добавляет новую заявку
     * @param item - заявка
     * @return новую заявку
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Method replace - заменяет заявку
     * @param id - идентификатор заявки
     * @param item - заявка
     */
    public void replace(String id, Item item) {
        for (int index = 0; index < this.position; index++) {
            if (this.items[index].getId().equals(id)) {
                this.items[index].setName(item.getName());
                this.items[index].setDescription(item.getDescription());
                break;
            }
        }
    }

    /**
     * Method delete - удаляет заявку
     * @param id - ид заявки
     */
    public void delete(String id) {
        for (int index = 0; index < this.position; index++) {
            if (this.items[index] != null && this.items[index].getId().equals(id)) {
                System.arraycopy(this.items, index + 1, this.items, index, this.items.length - index - 1);
                this.position--;
            }
        }
    }

    /**
     * Method findAll - ищет все заявки
     * @return - массив всех заявок
     */
    public Item[] findAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index < this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }

    /**
     * Method findByName - ищет заявки по имени
     * @param key - ключ для поиска
     * @return - массив соответствующих ключу заявок
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[this.position];
        int index = 0;
        for (Item item : this.items) {
            if (item != null && item.getName().equals(key)) {
                result[index] = item;
                index++;
            }
        }
        return Arrays.copyOf(result, index);
    }

    /**
     * Method findById - ищет заявку по идентификатору
     * @param id - идентификатор
     * @return - заявка
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
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
