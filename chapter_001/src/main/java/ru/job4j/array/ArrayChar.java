package ru.job4j.array;

/**
 * ArrayChar - проверка, что слово начинается с префикса
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ArrayChar {

    /**
     * Символьный массив
     */
    private char[] data;

    /**
     * Конструктор - создание символьного массива
     * @param line - строка для массива
     */
    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Конструктор без параметров - массив data не инициализируется
     */
    public ArrayChar() {

    }

    /**
     * Method startWith - проверяет, что слово начинается с префикса
     * @param prefix - префикс
     * @return результат проверки
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        if (value.length > this.data.length) {
            result = false;
        } else {
            for (int index = 0; index < value.length; index++) {
                if (value[index] != this.data[index]) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Method contains - поиск подстроки в строке
     * @param origin - строка
     * @param sub - подстрока
     * @return результат поиска
     */
    public boolean contains(String origin, String sub) {
        boolean result = false;
        char[] originArray = origin.toCharArray();
        char[] subArray = sub.toCharArray();
        if (subArray.length <= originArray.length) {
            for (int shift = 0; shift <= originArray.length - subArray.length; shift++) {
                for (int entry = 0; entry < subArray.length; entry++) {
                    if (originArray[shift + entry] != subArray[entry]) {
                        break;
                    } else {
                        if (entry == subArray.length - 1) {
                            result = true;
                        }
                    }
                }
            }
        }
        return result;
    }
}
