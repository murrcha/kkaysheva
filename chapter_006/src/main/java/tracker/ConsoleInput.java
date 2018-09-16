package tracker;

import java.util.Scanner;

/**
 * ConsoleInput - запрос данных от пользователя через консоль
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ConsoleInput implements Input {

    /**
     * Объект для считывания стандартного потока ввода
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Method ask - запрашивает данные у пользователя через консоль
     * @param question - запрос данных
     * @return ответ пользователя
     */
    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * Method ask - запрашивает данные у пользователя через консоль
     * @param question - запрос
     * @param range - диапазон
     * @return -
     */
    @Override
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Номер пункта меню вне диапазона");
        }
        return key;
    }
}
