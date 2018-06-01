package ru.job4j.parserbrackets;

import java.util.Stack;

/**
 * ParserMultiBrackets - парсер разных скобок
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ParserMultiBrackets {

    /**
     * Скобки
     */
    private final Brackets brackets;

    /**
     * Конструктор с инициализацией скобок
     * @param brackets
     */
    public ParserMultiBrackets(Brackets brackets) {
        this.brackets = brackets;
    }

    /**
     * Method validateBrackets проверяет парность заданных скобок
     *
     * @param input строка
     * @return результат
     */
    public boolean validateBrackets(String input) {
        boolean result = false;
        Stack<String> stackBrackets = new Stack<>();
        String subString;
        boolean balanced = true;
        for (int index = 0; index < input.length() - brackets.getOpeningBracket().length(); index++) {
            subString = input.substring(index, index + brackets.getOpeningBracket().length());
            if (brackets.isOpeningBracket(subString)) {
                stackBrackets.push(subString);
                continue;
            } else {
                subString = input.substring(index, index + brackets.getClosingBracket().length());
                if (brackets.isClosingBracket(subString)) {
                    if (stackBrackets.empty()) {
                        balanced = false;
                        break;
                    } else {
                        if (brackets.isPair(stackBrackets.peek(), subString)) {
                            stackBrackets.pop();
                            continue;
                        } else {
                            balanced = false;
                            break;
                        }
                    }
                }
            }
        }
        if (balanced && stackBrackets.empty()) {
            result = true;
        }
        return result;
    }

    /**
     * Method parseBrackets возвращает массив пар скобок с позициями в виде строки
     *
     * @param input строка
     * @return массив либо null
     */
    public String[] parseBrackets(String input) {
        String[] bracketsArray = null;
        if (this.validateBrackets(input) && input.length() != 0) {
            Stack<String> stackBrackets = new Stack<>();
            Stack<Integer> stackPosition = new Stack<>();
            bracketsArray = new String[input.length() / 2];
            int count = 0;
            String subString;
            for (int index = 0; index < input.length() - brackets.getOpeningBracket().length(); index++) {
                subString = input.substring(index, index + brackets.getOpeningBracket().length());
                if (brackets.isOpeningBracket(subString)) {
                    stackBrackets.push(subString);
                    stackPosition.push(index);
                    continue;
                } else {
                    subString = input.substring(index, index + brackets.getClosingBracket().length());
                    if (brackets.isClosingBracket(subString)) {
                        bracketsArray[count] =
                                String.format("%s%s, %s%s", stackBrackets.peek(),
                                        stackPosition.peek(), index, subString);
                        stackBrackets.pop();
                        stackPosition.pop();
                        count++;
                    }
                }
            }
        }
        if (bracketsArray != null && bracketsArray.length == 0) {
            bracketsArray = null;
        }
        return bracketsArray;
    }
}
