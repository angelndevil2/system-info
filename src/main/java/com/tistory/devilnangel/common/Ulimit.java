package com.tistory.devilnangel.common;

import com.tistory.devilnangel.util.SystemRun;
import org.apache.commons.exec.CommandLine;

import java.io.IOException;

/**
 *
 * Os's ulimit checker
 *
 * @author k, Created on 16. 1. 23.
 */
public class Ulimit {

    public static final String ULIMIT_COMMAND = "ulimit";
    public static final String OPTION_OPEN_FILES = "-n";

    /**
     * block while command 'ulimit -n' is running
     *
     * @return shell result string of "ulimit -n"
     * @throws IOException
     * @throws InterruptedException
     */
    public String getOpenFiles() throws IOException, InterruptedException {

        CommandLine ulimit_open_files = new CommandLine(ULIMIT_COMMAND);
        ulimit_open_files.addArgument(OPTION_OPEN_FILES);

        SystemRun.ResultHandler result_handler = SystemRun.execCommand(ulimit_open_files);
        result_handler.waitFor();
        return result_handler.getResultString();
    }
}
