package ru.job4j.io.archive;

import org.apache.commons.cli.*;

import java.util.Arrays;
import java.util.List;

/**
 * Args
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 02.2019
 */
public class Args {

    private String directory;
    private String include;
    private String output;

    public Args(String[] arguments) throws MissingOptionException {
        parseArguments(arguments);
    }

    public String directory() {
        return directory;
    }

    public List<String> include() {
        return Arrays.asList(include.split("\\."));
    }

    public String output() {
        return output;
    }

    private void parseArguments(String[] args) throws MissingOptionException {
        Options options = new Options();

        Option directory = new Option("d", "directory", true, "directory for zip");
        directory.setRequired(true);
        options.addOption(directory);

        Option include = new Option("i", "include", true, "include files exts");
        include.setRequired(true);
        options.addOption(include);

        Option output = new Option("o", "output", true, "output zip file");
        output.setRequired(true);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
            this.directory = cmd.getOptionValue("directory").trim();
            this.include = cmd.getOptionValue("include").trim();
            this.output = cmd.getOptionValue("output").trim();
        } catch (ParseException e) {
            formatter.printHelp("utility-name", options);
            throw new MissingOptionException("Missing option");
        }
    }
}
