package ru.job4j.parserbrackets;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * ParserBrackets Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ParserBracketsTest {

    /**
     * Test validate valid input
     */
    @Test
    public void whenValidateValidInputThenReturnTrue() {
        ParserBrackets testParser = new ParserBrackets();
        String firstInput = "{a}{c}{d}[f](a)";
        String secondInput = "a{[[(a)]]c}[g]";
        assertThat(testParser.validateString(firstInput), is(true));
        assertThat(testParser.validateString(secondInput), is(true));
    }

    /**
     * Test validate invalid input
     */
    @Test
    public void whenValidateInvalidInputThenReturnFalse() {
        ParserBrackets testParser = new ParserBrackets();
        String firstInput = "{f)}[[[k](l)";
        String secondInput = "a{de]}(cd)[f[f]";
        assertThat(testParser.validateString(firstInput), is(false));
        assertThat(testParser.validateString(secondInput), is(false));
    }

    /**
     * Test parse invalid input
     */
    @Test
    public void whenParseInvalidInputThenReturnNull() {
        ParserBrackets testParser = new ParserBrackets();
        String firstInput = "{(s}[d)](d)";
        String secondInput = "g";
        assertThat(testParser.parseString(firstInput), nullValue());
        assertThat(testParser.parseString(secondInput), nullValue());
    }

    /**
     * Test parse valid string
     */
    @Test
    public void whenParseValidStringThenReturnStringArrayBrackets() {
        ParserBrackets testParser = new ParserBrackets();
        String firstInput = "{[(ac)]}(d)f";
        String[] result = testParser.parseString(firstInput);
        assertThat(result[0], is("(2, 5)"));
        assertThat(result[1], is("[1, 6]"));
        assertThat(result[2], is("{0, 7}"));
        assertThat(result[3], is("(8, 10)"));
    }
}
