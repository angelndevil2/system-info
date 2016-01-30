package com.tistory.devilnangel;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.apache.commons.cli.*;

/**
 * @author k, Created on 16. 1. 27.
 */
@Data
public class CmdOptions {

    private final Options options = new Options();
    private final CommandLineParser parser = new DefaultParser();
    @Setter(AccessLevel.NONE)
    private CommandLine cmd;
    private String[] args;

    public CmdOptions() {

        options.addOption("s", "server", false, "run in server mode");
        options.addOption("c", "cpu", false, "print memory information");
        options.addOption("m", "mem", false, "print memory information");
        options.addOption("o", "os", false, "print os information");
        options.addOption("h", "help", false, "print this message");
        options.addOption("d", "base.dir", true, "system-info base directory");
        options.addOption(
                Option.builder("d").
                        argName("file").
                        numberOfArgs(1).
                        desc("system-info base directory").build());
        options.addOption(
                Option.builder("p").
                        argName("port").
                        numberOfArgs(1).
                        desc("rmi server port").build());
/*        options.addOption(
                Option.builder("X").
                        argName("ms=value").
                        numberOfArgs(2).
                        valueSeparator().
                        desc("value must be ##m").build());*/
    }

    /**
     * set commaond line arguments and parse
     *
     * @param args
     * @throws ParseException
     */
    public void setArgs(String[] args) throws ParseException {
        this.args = args;
        cmd = parser.parse(options, args);
    }

    /**
     * print usage and help information
     */
    public void printUsage() {
        // automatically generate the help statement
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("system-info", options, true);
    }
}
