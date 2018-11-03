package ru.job4j.lambda;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.StubInput;
import ru.job4j.tracker.Tracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * StartUI Test (lambda)
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version 0.1
 * @since 03.11.2018
 */
public class StartUITest {

    private Tracker testTracker = new Tracker();
    private Input testInput;
    private final PrintStream stdOut = System.out;
    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    String menu = new StringBuilder()
            .append(System.lineSeparator())
            .append("---------Меню:-----------")
            .append(System.lineSeparator())
            .append("0. Добавление новой заявки")
            .append(System.lineSeparator())
            .append("1. Вывод всех заявок")
            .append(System.lineSeparator())
            .append("2. Редактирование заявки")
            .append(System.lineSeparator())
            .append("3. Удаление заявки")
            .append(System.lineSeparator())
            .append("4. Поиск заявки по ID")
            .append(System.lineSeparator())
            .append("5. Поиск заявок по имени")
            .append(System.lineSeparator())
            .append("6. Выход из программы")
            .append(System.lineSeparator())
            .append("-------------------------")
            .append(System.lineSeparator())
            .append(System.lineSeparator())
            .toString();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void backOutput() {
        System.setOut(stdOut);
    }

    /**
     * Test add new item
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        testInput = new StubInput(new String[] {"0", "name1", "desc1", "6"});
        new StartUI(testInput, testTracker).init();
        String testId = testTracker.findByName("name1").get(0).getId();
        assertThat(new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("----------Добавление новой заявки----------")
                                .append(System.lineSeparator())
                                .append("Добавлена новая заявка ID: ")
                                .append(testId)
                                .append("| Имя: name1| Описание: desc1")
                                .append(System.lineSeparator())
                                .append(menu)
                                .toString()
                )
        );
    }

    /**
     * Test edit item
     */
    @Test
    public void whenUserEditItemThenTrackerUpdateThisItem() {
        Item testItem = testTracker.add(new Item("test", "test"));
        String testId = testItem.getId();
        testInput = new StubInput(new String[] {"2", testId, "name1", "desc1", "6"});
        new ru.job4j.tracker.StartUI(testInput, testTracker).init();
        assertThat(new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("----------Редактирование заявки----------")
                                .append(System.lineSeparator())
                                .append("Изменена заявка ID: ")
                                .append(testId)
                                .append("| Имя: name1| Описание: desc1")
                                .append(System.lineSeparator())
                                .append(menu)
                                .toString()
                )
        );
    }

    /**
     * Test edit item with fake id
     */
    @Test
    public void whenUserEditItemWithFakeIdThenTrackerNotFoundItem() {
        testInput = new StubInput(new String[] {"2", "123", "6"});
        new ru.job4j.tracker.StartUI(testInput, testTracker).init();
        assertThat(new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("----------Редактирование заявки----------")
                                .append(System.lineSeparator())
                                .append("Заявка ID: 123 не найдена")
                                .append(System.lineSeparator())
                                .append(menu)
                                .toString()
                )
        );
    }

    /**
     * Test delete item
     */
    @Test
    public void whenUserDeleteItemThenTrackerDeleteThisItem() {
        Item testItem = testTracker.add(new Item("test", "test"));
        String testId = testItem.getId();
        testInput = new StubInput(new String[] {"3", testId, "6"});
        new ru.job4j.tracker.StartUI(testInput, testTracker).init();
        assertThat(new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("----------Удаление заявки----------")
                                .append(System.lineSeparator())
                                .append("Удалена заявка ID: ")
                                .append(testId)
                                .append(System.lineSeparator())
                                .append(menu)
                                .toString()
                )
        );
    }

    /**
     * Test delete item with fake id
     */
    @Test
    public void whenUserDeleteItemWithFakeIdThenTrackerNotFoundItem() {
        testInput = new StubInput(new String[] {"3", "123", "6"});
        new ru.job4j.tracker.StartUI(testInput, testTracker).init();
        assertThat(new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("----------Удаление заявки----------")
                                .append(System.lineSeparator())
                                .append("Заявка ID: 123 не найдена")
                                .append(System.lineSeparator())
                                .append(menu)
                                .toString()
                )
        );
    }

    /**
     * Test showAllItems when no items
     */
    @Test
    public void whenUserFindAllItemsAndNoItemsThenNotFoundItems() {
        testInput = new StubInput(new String[] {"1", "6"});
        new ru.job4j.tracker.StartUI(testInput, testTracker).init();
        assertThat(new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("----------Вывод всех заявок----------")
                                .append(System.lineSeparator())
                                .append("Заявок нет")
                                .append(System.lineSeparator())
                                .append(menu)
                                .toString()
                )
        );
    }

    /**
     * Test showAllItems
     */
    @Test
    public void whenUserFindAllItemsThenShowAllItems() {
        Item testItem = testTracker.add(new Item("test", "test"));
        testInput = new StubInput(new String[] {"1", "6"});
        new ru.job4j.tracker.StartUI(testInput, testTracker).init();
        assertThat(new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("----------Вывод всех заявок----------")
                                .append(System.lineSeparator())
                                .append("ID: ")
                                .append(testItem.getId())
                                .append("| Имя: test| Описание: test")
                                .append(System.lineSeparator())
                                .append(menu)
                                .toString()
                )
        );
    }

    /**
     * Test findItemByName
     */
    @Test
    public void whenUserFindItemByNameThenShowThisItem() {
        Item testItem = testTracker.add(new Item("test", "test"));
        testInput = new StubInput(new String[] {"5", "test", "6"});
        new ru.job4j.tracker.StartUI(testInput, testTracker).init();
        assertThat(new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("----------Поиск заявок по имени----------")
                                .append(System.lineSeparator())
                                .append("Заявки с именем test:")
                                .append(System.lineSeparator())
                                .append("ID: ")
                                .append(testItem.getId())
                                .append("| Имя: test| Описание: test")
                                .append(System.lineSeparator())
                                .append(menu)
                                .toString()
                )
        );
    }

    /**
     * Test findItemByName with fake name
     */
    @Test
    public void whenUserFindItemByFakeNameThenNotFoundItem() {
        testInput = new StubInput(new String[] {"5", "name1", "6"});
        new ru.job4j.tracker.StartUI(testInput, testTracker).init();
        assertThat(new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("----------Поиск заявок по имени----------")
                                .append(System.lineSeparator())
                                .append("Заявка с именем name1 не найдена")
                                .append(System.lineSeparator())
                                .append(menu)
                                .toString()
                )
        );
    }

    /**
     * Test findItemById
     */
    @Test
    public void whenUserFindItemByIdThenShowThisItem() {
        Item testItem = testTracker.add(new Item("test", "test"));
        String testId = testItem.getId();
        testInput = new StubInput(new String[] {"4", testId, "6"});
        new ru.job4j.tracker.StartUI(testInput, testTracker).init();
        assertThat(new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("----------Поиск заявки по ID----------")
                                .append(System.lineSeparator())
                                .append("Найдена заявка ID: ")
                                .append(testId)
                                .append("| Имя: test| Описание: test")
                                .append(System.lineSeparator())
                                .append(menu)
                                .toString()
                )
        );
    }

    /**
     * Test findItemById with fake id
     */
    @Test
    public void whenUserFindItemByIdWithFakeIdThenNotFoundItem() {
        testInput = new StubInput(new String[] {"4", "123", "6"});
        new ru.job4j.tracker.StartUI(testInput, testTracker).init();
        assertThat(new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("----------Поиск заявки по ID----------")
                                .append(System.lineSeparator())
                                .append("Заявка ID: 123 не найдена")
                                .append(System.lineSeparator())
                                .append(menu)
                                .toString()
                )
        );
    }
}