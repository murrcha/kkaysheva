package ru.job4j.io.archive;

import org.apache.commons.cli.MissingOptionException;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * ArgsTest
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 02.2019
 */
public class ArgsTest {

    @Test
    public void parseArguments() throws MissingOptionException {
        String[] args = {"-d /home/kkaysheva/projects/project1", "-i java.xml", "-o project1.zip"};
        Args argsParser = new Args(args);
        assertThat(argsParser.directory(), is("/home/kkaysheva/projects/project1"));
        assertThat(argsParser.include().get(0), is("java"));
        assertThat(argsParser.include().get(1), is("xml"));
        assertThat(argsParser.output(), is("project1.zip"));
    }

    @Test(expected = MissingOptionException.class)
    public void parseArgumentsError() throws MissingOptionException {
        String[] args = {"-d /home/kkaysheva/projects/project1", "-o project1.zip"};
        new Args(args);
    }
}