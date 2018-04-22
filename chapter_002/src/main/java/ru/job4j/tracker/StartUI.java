package ru.job4j.tracker;

/**
 * StartUI - точка входа в программу
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class StartUI {

    /**
     * Константы меню:
     * ADD - для добавления новой заявки
     * SHOW_ALL - для вывода всех заявок
     * EDIT - для редактирования заявки
     * DELETE - для удаления заявки
     * FIND_BY_ID - для поиска зявки по ID
     * FIND_BY_NAME - для поиска заявок по имени
     * EXIT - для выхода из программы
     */
    private static final String ADD = "0";
    private static final String SHOW_ALL = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FIND_BY_ID = "4";
    private static final String FIND_BY_NAME = "5";
    private static final String EXIT = "6";

    /**
     * Интерфейс для получения данных от пользователя
     */
    private final Input input;
    /**
     * Хранилище заявок
     */
    private final Tracker tracker;

    /**
     * Конструктор - инициализирует поля
     * @param input - ввод данных
     * @param tracker - хранилище заявок
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Method init - реализует основной цикл программы
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню: ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW_ALL.equals(answer)) {
                this.showAllItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FIND_BY_ID.equals(answer)) {
                this.findItemById();
            } else if (FIND_BY_NAME.equals(answer)) {
                this.findItemByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Method createItem - добавление новой заявки в хранилище
     */
    private void createItem() {
        System.out.println("----------Добавление новой заявки----------");
        String name = this.input.ask("Введите имя заявки: ");
        String description = this.input.ask("Введите описание заявки: ");
        Item item = new Item(name, description);
        this.tracker.add(item);
        System.out.println("Добавлена новая заявка ID: " + item.getId() + "| Имя: " + item.getName() + "| Описание: " + item.getDescription());
    }

    /**
     * Method showAllItems - выводит все заявки из хранилища
     */
    private void showAllItems() {
        System.out.println("----------Вывод всех заявок----------");
        Item[] items = this.tracker.findAll();
        if (items.length == 0) {
            System.out.println("Заявок нет");
        } else {
            for (Item item : items) {
                System.out.println("ID: " + item.getId() + "| Имя: " + item.getName() + "| Описание: " + item.getDescription());
            }
        }
    }

    /**
     * Method editItem - редактирование заявки
     */
    private void editItem() {
        System.out.println("----------Редактирование заявки----------");
        String id = this.input.ask("Введите ID заявки: ");
        Item existingItem = this.tracker.findById(id);
        if (existingItem != null) {
            String name = this.input.ask("Введите новое имя заявки: ");
            String description = this.input.ask("Введите новое описание заявки: ");
            Item newItem = new Item(name, description);
            this.tracker.replace(existingItem.getId(), newItem);
            System.out.println("Изменена заявка ID: " + newItem.getId() + "| Имя: " + newItem.getName() + "| Описание: " + newItem.getDescription());
        } else {
            System.out.println("Заявка ID: " + id + " не найдена");
        }
    }

    /**
     * Method deleteItem - удаление заявки
     */
    private void deleteItem() {
        System.out.println("----------Удаление заявки----------");
        String id = this.input.ask("Введите ID заявки: ");
        Item existingItem = this.tracker.findById(id);
        if (existingItem != null) {
            this.tracker.delete(existingItem.getId());
            System.out.println("Удалена заявка ID: " + id);
        } else {
            System.out.println("Заявка ID: " + id + " не найдена");
        }
    }

    /**
     * Method findItemById - поиск заявки по ID
     */
    private void findItemById() {
        System.out.println("----------Поиск заявки по ID----------");
        String id = this.input.ask("Введите ID заявки: ");
        Item existingItem = this.tracker.findById(id);
        if (existingItem != null) {
            System.out.println("Найдена заявка ID: " + existingItem.getId() + "| Имя: " + existingItem.getName() + "| Описание: " + existingItem.getDescription());
        } else {
            System.out.println("Заявка ID: " + id + " не найдена");
        }
    }

    /**
     * Method findItemByName
     */
    private void findItemByName() {
        System.out.println("----------Поиск заявок по имени----------");
        String name = this.input.ask("Введите имя заявки: ");
        Item[] items = this.tracker.findByName(name);
        if (items.length == 0) {
            System.out.println("Заявка с именем " + name + " не найдена");
        } else {
            System.out.println("Заявки с именем " + name + ":");
            for (Item item : items) {
                System.out.println("ID: " + item.getId() + "| Имя: " + item.getName() + "| Описание: " + item.getDescription());
            }
        }
    }

    /**
     * Method showMenu - выводит меню в консоль
     */
    private void showMenu() {
        System.out.println("---------Меню:-----------");
        System.out.println("0. Добавление новой заявки");
        System.out.println("1. Вывод всех заявок");
        System.out.println("2. Редактирование заявки");
        System.out.println("3. Удаление заявки");
        System.out.println("4. Поиск заявки по ID");
        System.out.println("5. Поиск заявок по имени");
        System.out.println("6. Выход из программы");
        System.out.println("-------------------------");
        System.out.println();
    }

    /**
     * Method main - запуск программы
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
