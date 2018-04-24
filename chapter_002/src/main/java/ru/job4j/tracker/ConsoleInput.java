package ru.job4j.tracker;

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
}
