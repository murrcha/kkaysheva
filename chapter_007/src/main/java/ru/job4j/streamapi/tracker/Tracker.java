package ru.job4j.streamapi.tracker;

import ru.job4j.tracker.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Tracker - хранилище для заявок (stream api)
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version 0.1
 * @since 03.11.2018
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
        this.items.stream()
                .filter(itm -> itm.getId().equals(id))
                .forEach(itm -> {
                    itm.setName(item.getName());
                    itm.setDescription(item.getDescription());
                });
    }

    /**
     * Method delete - удаляет заявку
     * @param id - ид заявки
     */
    public void delete(String id) {
        IntStream.range(0, items.size())
                .filter(index -> index < items.size())
                .filter(index -> items.get(index).getId().equals(id))
                .forEach(items::remove);
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
        return this.items.stream()
                .filter(item -> item.getName().contains(key))
                .collect(Collectors.toList());
    }

    /**
     * Method findById - ищет заявку по идентификатору
     * @param id - идентификатор
     * @return - заявка
     */
    public Item findById(String id) {
        Optional<Item> item = this.items.stream()
                .filter(itm -> itm.getId().equals(id))
                .findFirst();
        return item.orElse(null);
    }

    /**
     * Method generateId - генерирует идентификатор заявки
     * @return идентификатор заявки
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
}
