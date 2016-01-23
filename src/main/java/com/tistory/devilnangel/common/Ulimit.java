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

    /**
     * block while command 'ulimit -n' is running
     *
     * @return shell result string of "ulimit -n"
     * @throws IOException
     * @throws InterruptedException
     */
    public int getOpenFiles() throws IOException, InterruptedException {

        CommandLine ulimit_open_files = new CommandLine("ulimit-n");

        try {

            SystemRun.ResultHandler result_handler = SystemRun.execCommand(ulimit_open_files);
            result_handler.waitFor();

            if (result_handler.getExitValue() !=0) {
                System.err.println("result = " + result_handler.getResultString().trim());
                return 0;
            } else {
                return Integer.valueOf(result_handler.getResultString().trim());
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return 0;
    }
}
