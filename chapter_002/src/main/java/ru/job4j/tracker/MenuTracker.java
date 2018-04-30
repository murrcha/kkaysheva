package ru.job4j.tracker;

/**
 * EditItem - редактирование заявки
 */
class EditItem implements UserAction {

    @Override
    public int key() {
        return 2;
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

    @Override
    public String info() {
        return String.format("%s. Редактирование заявки", this.key());
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
    private UserAction[] actions = new UserAction[7];

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
     * Method fillAction - заполняет массив действий пользователя (пункты меню)
     */
    public void fillActions() {
        this.actions[0] = this.new AddItem();
        this.actions[1] = new MenuTracker.ShowItems();
        this.actions[2] = new EditItem();
        this.actions[3] = this.new DeleteItem();
        this.actions[4] = this.new FindItemById();
        this.actions[5] = this.new FindItemsByName();
        this.actions[6] = this.new Exit();
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
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * AddItem - добавление новой заявки
     */
    private class AddItem implements UserAction {

        @Override
        public int key() {
            return 0;
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

        @Override
        public String info() {
            return String.format("%s. Добавление новой заявки", this.key());
        }
    }

    /**
     * ShowItems - вывод всех заявок
     */
    private static class ShowItems implements UserAction {

        @Override
        public int key() {
            return 1;
        }

        /**
         * Method execute - выполняет действие вывод всех заявок
         * @param input - ввод
         * @param tracker - хранилище
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----------Вывод всех заявок----------");
            Item[] items = tracker.findAll();
            if (items.length == 0) {
                System.out.println("Заявок нет");
            } else {
                for (Item item : items) {
                    System.out.println(String.format("ID: %s| Имя: %s| Описание: %s", item.getId(), item.getName(), item.getDescription()));
                }
            }
        }

        @Override
        public String info() {
            return String.format("%s. Вывод всех заявок", this.key());
        }
    }

    /**
     * DeleteItem - удаление заявки
     */
    private class DeleteItem implements UserAction {

        @Override
        public int key() {
            return 3;
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

        @Override
        public String info() {
            return String.format("%s. Удаление заявки", this.key());
        }
    }

    /**
     * FindItemById - поиск заявки по ID
     */
    private class FindItemById implements UserAction {

        @Override
        public int key() {
            return 4;
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

        @Override
        public String info() {
            return String.format("%s. Поиск заявки по ID", this.key());
        }
    }

    private class FindItemsByName implements UserAction {

        @Override
        public int key() {
            return 5;
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
            Item[] items = tracker.findByName(name);
            if (items.length == 0) {
                System.out.println(String.format("Заявка с именем %s не найдена", name));
            } else {
                System.out.println(String.format("Заявки с именем %s:", name));
                for (Item item : items) {
                    System.out.println(String.format("ID: %s| Имя: %s| Описание: %s", item.getId(), item.getName(), item.getDescription()));
                }
            }
        }

        @Override
        public String info() {
            return String.format("%s. Поиск заявок по имени", this.key());
        }

    }

    private class Exit implements UserAction {

        @Override
        public int key() {
            return 6;
        }

        /**
         * Method execute - выполняет действие выход из программы
         * @param input ввод
         * @param tracker хранилище
         */
        @Override
        public void execute(Input input, Tracker tracker) {

        }

        @Override
        public String info() {
            return String.format("%s. Выход из программы", this.key());
        }
    }
}
