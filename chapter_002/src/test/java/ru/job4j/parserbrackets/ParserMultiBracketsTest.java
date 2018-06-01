package ru.job4j.parserbrackets;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * ParserMultiBrackets Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ParserMultiBracketsTest {

    /**
     * Test validate
     */
    @Test
    public void whenValidInputValidateThenReturnTrue() {
        Brackets brackets = new Brackets("<h>", "</h>");
        ParserMultiBrackets parserBrackets = new ParserMultiBrackets(brackets);
        String input = "text<h>text</h>text";
        assertThat(parserBrackets.validateBrackets(input), is(true));
    }

    /**
     * Test validate
     */
    @Test
    public void whenInvalidInputValidateThenReturnFalse() {
        Brackets brackets = new Brackets("<h>", "</h>");
        ParserMultiBrackets parserBrackets = new ParserMultiBrackets(brackets);
        String input = "text<h>text</h>t</h>";
        assertThat(parserBrackets.validateBrackets(input), is(false));
    }

    @Test
    public void whenParseInputThenReturnStringArray() {
        /*Brackets brackets = new Brackets("<h>", "</h>");
        ParserMultiBrackets parserBrackets = new ParserMultiBrackets(brackets);
        String input = "<h>text<h></h></h>";
        String[] result = parserBrackets.parseBrackets(input);
        assertThat(result[0], is("<h>7, 10</h>"));
        assertThat(result[1], is("<h>0, 13</h>"));*/
    }
}
