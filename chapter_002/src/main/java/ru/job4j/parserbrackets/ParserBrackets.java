package ru.job4j.parserbrackets;

import java.util.Stack;

/**
 * ParserBrackets - парсер скобок
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ParserBrackets {

    /**
     * Константы:
     * SQUARE_BRACKETS - квадраные скобки
     * FIGURE_BRACKETS - фигурные скобки
     * ROUND_BRACKETS - круглые скобки
     */
    private final static char[] SQUARE_BRACKETS = "[]".toCharArray();
    private final static char[] FIGURE_BRACKETS = "{}".toCharArray();
    private final static char[] ROUND_BRACKETS = "()".toCharArray();

    /**
     * Method isPair - проверяет, являются символы парой одинаковых скобок
     * @param openingSymbol символ с открывающей скобкой
     * @param closingSymbol символ с закрывающей скобкой
     * @return результат
     */
    private boolean isPair(char openingSymbol, char closingSymbol) {
        return ((openingSymbol == SQUARE_BRACKETS[0] && closingSymbol == SQUARE_BRACKETS[1])
                || (openingSymbol == FIGURE_BRACKETS[0] && closingSymbol == FIGURE_BRACKETS[1])
                || (openingSymbol == ROUND_BRACKETS[0] && closingSymbol == ROUND_BRACKETS[1]));
    }

    /**
     * Method isOpeningBracket - проверяет, является ли символ открывающей скобкой
     * @param symbol
     * @return
     */
    private boolean isOpeningBracket(char symbol) {
        return (symbol == SQUARE_BRACKETS[0] || symbol == FIGURE_BRACKETS[0] || symbol == ROUND_BRACKETS[0]);
    }

    /**
     * Method isClosingBrackets - проверяет, является ли символ закрывающей скобкой
     * @param symbol
     * @return
     */
    private boolean isClosingBracket(char symbol) {
        return (symbol == SQUARE_BRACKETS[1] || symbol == FIGURE_BRACKETS[1] || symbol == ROUND_BRACKETS[1]);
    }

    /**
     * Method validateString - проверяет валидность расположения скобок в строке
     * @param input строка для проверки
     * @return результат (пустая строка или строка без скобок считается валидной)
     */
    public boolean validateString(String input) {
        boolean result = false;
        Stack<Character> stackBrackets = new Stack<>();
        boolean balanced = true;
        for (int index = 0; index < input.length(); index++) {
            char symbol = input.charAt(index);
            if (isOpeningBracket(symbol)) {
                stackBrackets.push(symbol);
            } else if (isClosingBracket(symbol)) {
                if (stackBrackets.empty()) {
                    balanced = false;
                    break;
                } else if (isPair(stackBrackets.peek(), symbol)) {
                            stackBrackets.pop();
                } else {
                    balanced = false;
                    break;
                }
            }
        }
        if (balanced && stackBrackets.empty()) {
            result = true;
        }
        return result;
    }

    /**
     * Method parseString возвращает массив пар скобок с позициями в виде строки
     * @param input исходная строка
     * @return массив либо null
     */
    public String[] parseString(String input) {
        String[] brackets = null;
        if (validateString(input) && input.length() != 0) {
            Stack<Character> stackBrackets = new Stack<>();
            Stack<Integer> stackPosition = new Stack<>();
            brackets = new String[input.length() / 2];
            int count = 0;
            for (int index = 0; index < input.length(); index++) {
                char symbol = input.charAt(index);
                if (isOpeningBracket(symbol)) {
                    stackBrackets.push(symbol);
                    stackPosition.push(index);
                } else if (isClosingBracket(symbol)) {
                    brackets[count] = String.format("%s%s, %s%s", stackBrackets.peek(), stackPosition.peek(), index, symbol);
                    stackBrackets.pop();
                    stackPosition.pop();
                    count++;
                }
            }
            if (brackets.length == 0) {
                brackets = null;
            }
        }
        return brackets;
    }
}
