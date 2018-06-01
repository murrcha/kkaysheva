package ru.job4j.parserbrackets;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

/**
 * ParserMultiBrackets Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ParserMultiBracketsTest {

    /**
     * Test validateBrackets
     */
    @Test
    public void whenValidInputValidateThenReturnTrue() {
        ParserMultiBrackets parserBrackets = new ParserMultiBrackets(
                new Brackets("<tag>", "</tag>")
        );
        String input = "text<tag>text<tag>text</tag>text</tag>text";
        assertThat(parserBrackets.validateBrackets(input), is(true));
    }

    /**
     * Test validateBrackets
     */
    @Test
    public void whenInvalidInputValidateThenReturnFalse() {
        ParserMultiBrackets parserBrackets = new ParserMultiBrackets(
                new Brackets("<h>", "</h>")
        );
        String input = "</h>text<h>text</h>";
        assertThat(parserBrackets.validateBrackets(input), is(false));
    }

    /**
     * Test parseBrackets
     */
    @Test
    public void whenParseValidInputThenReturnStringArray() {
        Brackets brackets = new Brackets("<h>", "</h>");
        ParserMultiBrackets parserBrackets = new ParserMultiBrackets(brackets);
        String input = "tag<h>tag<h>test</h>tag</h>tag";
        String[] result = parserBrackets.parseBrackets(input);
        assertThat(result[0], is("<h>9, 16</h>"));
        assertThat(result[1], is("<h>3, 23</h>"));
    }

    /**
     * Test parseBrackets
     */
    @Test
    public void whenParseInvalidInputThenReturnNull() {
        Brackets brackets = new Brackets("<h>", "</h>");
        ParserMultiBrackets parserBrackets = new ParserMultiBrackets(brackets);
        String invalidInput = "</h>text<h></h></h>";
        String emptyInput = "m";
        assertThat(parserBrackets.parseBrackets(invalidInput), nullValue());
        assertThat(parserBrackets.parseBrackets(emptyInput), nullValue());
    }
}
