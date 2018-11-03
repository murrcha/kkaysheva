package ru.job4j.lambda;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.MenuOutException;

import java.util.Scanner;
import java.util.function.Consumer;

/**
 * ConsumerInput
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version 0.1
 * @since 03.11.2018
 */
public class ConsumerInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    private Consumer<String> consumer;

    public ConsumerInput(Consumer<String> consumer) {
        this.consumer = consumer;
    }

    @Override
    public String ask(String question) {
        consumer.accept(question);
        return scanner.nextLine();
    }

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
