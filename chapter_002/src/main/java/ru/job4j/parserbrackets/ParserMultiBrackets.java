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

    private final Brackets brackets;

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
        int index = 0;
        boolean balanced = true;
        do {
            if (input.contains(brackets.getOpeningBracket())) {
                index = input.indexOf(brackets.getOpeningBracket());
                subString = input.substring(index, index + brackets.getOpeningBracket().length());
                stackBrackets.push(subString);
                input = input.substring(index + subString.length());
            } else if (input.contains(brackets.getClosingBracket())) {
                if (stackBrackets.empty()) {
                    balanced = false;
                    break;
                } else {
                    index = input.indexOf(brackets.getClosingBracket());
                    subString = input.substring(index, index + brackets.getClosingBracket().length());
                    input = input.substring(index + subString.length());
                    if (brackets.isPair(stackBrackets.peek(), subString)) {
                        stackBrackets.pop();
                    } else {
                        balanced = false;
                        break;
                    }
                }
            }
        } while (input.contains(brackets.getOpeningBracket()) || input.contains(brackets.getClosingBracket()));
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
            int index;
            String subString;
            do {
                if (input.contains(brackets.getOpeningBracket())) {
                    index = input.indexOf(brackets.getOpeningBracket());
                    subString = input.substring(index, index + brackets.getOpeningBracket().length());
                    stackBrackets.push(subString);
                    stackPosition.push(index);
                    input = input.substring(index + subString.length());
                } else if (input.contains(brackets.getClosingBracket())) {
                    index = input.indexOf(brackets.getClosingBracket());
                    subString = input.substring(index, index + brackets.getClosingBracket().length());
                    input = input.substring(index + subString.length());
                    bracketsArray[count] = String.format("%s%s, %s%s", stackBrackets.peek(), stackPosition.peek(), index, subString);
                    stackBrackets.pop();
                    stackPosition.pop();
                    count++;
                }
            } while (input.contains(brackets.getOpeningBracket()) || input.contains(brackets.getClosingBracket()));
            if (bracketsArray.length == 0) {
                bracketsArray = null;
            }
        }
        return bracketsArray;
    }
}
