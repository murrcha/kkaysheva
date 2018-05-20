package ru.job4j.coffeemachine;

import java.util.Arrays;

/**
 * CoffeeMachine - кофе машина
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class CoffeeMachine {

    /**
     * Номиналы денежных монет и купюр
     */
    private final static int[] COINS = {1, 2, 5, 10};
    private final static int[] BILLS = {50, 100, 200, 500, 1000, 2000, 5000};

    /**
     * Method changes - возвращает сдачу монетами
     * @param price цена
     * @param value значение купюры
     * @return сдача
     */
    public int[] changes(int price, int value) {
        int[] change = null;
        if (price < value && this.isBills(value)) {
            change = new int[1];
            int difference = value - price;
            int count = 0;
            int coin;
            do {
                change = Arrays.copyOf(change, count + 1);
                coin = this.getCoin(difference);
                change[count] = coin;
                count++;
                difference = difference - coin;
            } while (difference != 0);
        }
        return change;
    }

    /**
     * Method getCoin - получает наибольший номинал монеты исходя из разницы
     * @param difference разница
     * @return номинал монеты
     */
    private int getCoin(int difference) {
        int result = 0;
        for (int index = 0; index < COINS.length; index++) {
            if (difference == COINS[index]) {
                result = COINS[index];
                break;
            } else if (difference < COINS[index]) {
                result = COINS[index - 1];
                break;
            } else if (index == COINS.length - 1) {
                result = COINS[index];
            }
        }
        return result;
    }

    /**
     * Method isBills - проверяет, что значение является одним из номиналов существующих купюр
     * @param value значение
     * @return результат
     */
    private boolean isBills(int value) {
        boolean result = false;
        for (int bill : BILLS) {
            if (bill == value) {
                result = true;
            }
        }
        return result;
    }
}
