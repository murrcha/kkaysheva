package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * EditItem - редактирование заявки
 */
class EditItem extends BaseAction {

    /**
     * Конструктор
     * @param key
     * @param name
     */
    public EditItem(int key, String name) {
        super(key, name);
    }

    /**
     * Method execute - выполняет действие редактирование заявки
     * @param input ввод
     * @param tracker хранилище
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("----------Редактирование заявки----------");
        String id = input.ask("Введите ID заявки: ");
        Item existingItem = tracker.findById(id);
        if (existingItem != null) {
            String name = input.ask("Введите новое имя заявки: ");
            String description = input.ask("Введите новое описание заявки: ");
            Item newItem = new Item(name, description);
            tracker.replace(existingItem.getId(), newItem);
            System.out.println(String.format("Изменена заявка ID: %s| Имя: %s| Описание: %s", existingItem.getId(), existingItem.getName(), existingItem.getDescription()));
        } else {
            System.out.println(String.format("Заявка ID: %s не найдена", id));
        }
    }
}

/**
 * MenuTracker - реализует меню
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class MenuTracker {

    /**
     * Диапазон допустимых номеров пунктов меню
     */
    private final static int[] RANGE = new int[] {0, 1, 2, 3, 4, 5, 6};

    /**
     * Ввод
     */
    private Input input;

    /**
     * Хранилище
     */
    private Tracker tracker;

    /**
     * Действие пользователя (пункты меню)
     */
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Позиция пункта меню
     */
    int position = 0;

    /**
     * Конструктор - инициализирует ввод и хранилище
     * @param input - ввод
     * @param tracker - хранилище
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Method getRange - возвращает значение поля range
     * @return range
     */
    public int[] getRange() {
        return MenuTracker.RANGE;
    }

    /**
     * Method fillAction - заполняет массив действий пользователя (пункты меню)
     */
    public void fillActions() {
        this.actions.add(this.new AddItem(position++, "Добавление новой заявки"));
        this.actions.add(new MenuTracker.ShowItems(position++, "Вывод всех заявок"));
        this.actions.add(new EditItem(position++, "Редактирование заявки"));
        this.actions.add(this.new DeleteItem(position++, "Удаление заявки"));
        this.actions.add(this.new FindItemById(position++, "Поиск заявки по ID"));
        this.actions.add(this.new FindItemsByName(position++, "Поиск заявок по имени"));
        this.actions.add(this.new Exit(position++, "Выход из программы"));
    }

    /**
     * Method show - выводит массив действий пользователя (пункты меню)
     */
    public void show() {
        System.out.println();
        System.out.println("---------Меню:-----------");
        for (UserAction action : actions) {
            System.out.println(action.info());
        }
        System.out.println("-------------------------");
        System.out.println();
    }

    /**
     * Method select - выполняет действие, выбранное пользователем
     * @param key
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * AddItem - добавление новой заявки
     */
    private class AddItem extends BaseAction {

        /**
         * Конструктор
         * @param key
         * @param name
         */
        public AddItem(int key, String name) {
            super(key, name);
        }

        /**
         * Method execute - выполняет действие добавление новой заявки
         * @param input - ввод
         * @param tracker - хранилище
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----------Добавление новой заявки----------");
            String name = input.ask("Введите имя заявки: ");
            String description = input.ask("Введите описание заявки: ");
            Item item = new Item(name, description);
            tracker.add(item);
            System.out.println(String.format("Добавлена новая заявка ID: %s| Имя: %s| Описание: %s", item.getId(), item.getName(), item.getDescription()));
        }
    }

    /**
     * ShowItems - вывод всех заявок
     */
    private static class ShowItems extends BaseAction {

        /**
         * Конструктор
         * @param key
         * @param name
         */
        public ShowItems(int key, String name) {
            super(key, name);
        }

        /**
         * Method execute - выполняет действие вывод всех заявок
         * @param input - ввод
         * @param tracker - хранилище
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----------Вывод всех заявок----------");
            List<Item> items = tracker.findAll();
            if (items.size() == 0) {
                System.out.println("Заявок нет");
            } else {
                for (Item item : items) {
                    System.out.println(String.format("ID: %s| Имя: %s| Описание: %s", item.getId(), item.getName(), item.getDescription()));
                }
            }
        }
    }

    /**
     * DeleteItem - удаление заявки
     */
    private class DeleteItem extends BaseAction {

        /**
         * Конструктор
         * @param key
         * @param name
         */
        public DeleteItem(int key, String name) {
            super(key, name);
        }

        /**
         * Method execute - выполняет действие удаление заявки
         * @param input ввод
         * @param tracker хранилище
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----------Удаление заявки----------");
            String id = input.ask("Введите ID заявки: ");
            Item existingItem = tracker.findById(id);
            if (existingItem != null) {
                tracker.delete(existingItem.getId());
                System.out.println(String.format("Удалена заявка ID: %s", id));
            } else {
                System.out.println(String.format("Заявка ID: %s не найдена", id));
            }
        }
    }

    /**
     * FindItemById - поиск заявки по ID
     */
    private class FindItemById extends BaseAction {

        /**
         * Конструктор
         * @param key
         * @param name
         */
        public FindItemById(int key, String name) {
            super(key, name);
        }

        /**
         * Method execute - выполняет действие поиск заявки по ID
         * @param input ввод
         * @param tracker хранилище
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----------Поиск заявки по ID----------");
            String id = input.ask("Введите ID заявки: ");
            Item existingItem = tracker.findById(id);
            if (existingItem != null) {
                System.out.println(String.format("Найдена заявка ID: %s| Имя: %s| Описание: %s", existingItem.getId(), existingItem.getName(), existingItem.getDescription()));
            } else {
                System.out.println(String.format("Заявка ID: %s не найдена", id));
            }
        }
    }

    /**
     * FindItemByName - поиск заявок по имени
     */
    private class FindItemsByName extends BaseAction {

        /**
         * Конструктор
         * @param key
         * @param name
         */
        public FindItemsByName(int key, String name) {
            super(key, name);
        }

        /**
         * Method execute - выполняет действие поиск заявок по имени
         * @param input ввод
         * @param tracker хранилище
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----------Поиск заявок по имени----------");
            String name = input.ask("Введите имя заявки: ");
            List<Item> items = tracker.findByName(name);
            if (items.size() == 0) {
                System.out.println(String.format("Заявка с именем %s не найдена", name));
            } else {
                System.out.println(String.format("Заявки с именем %s:", name));
                for (Item item : items) {
                    System.out.println(String.format("ID: %s| Имя: %s| Описание: %s", item.getId(), item.getName(), item.getDescription()));
                }
            }
        }
    }

    /**
     * Exit - выход из программы
     */
    private class Exit extends BaseAction {

        /**
         * Конструктор
         * @param key
         * @param name
         */
        public Exit(int key, String name) {
            super(key, name);
        }

        /**
         * Method execute - выполняет действие выход из программы
         * @param input ввод
         * @param tracker хранилище
         */
        @Override
        public void execute(Input input, Tracker tracker) {
        }
    }
}
