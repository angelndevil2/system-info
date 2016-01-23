package com.tistory.devilnangel.test;

import com.tistory.devilnangel.util.SystemRun;
import org.junit.Test;

/**
 * @author k, Created on 16. 1. 23.
 */
public class SystemRunTest {

    @Test
    public void printLs() {

        try {
            SystemRun.ResultHandler handler = SystemRun.execCommand("ls");
            handler.waitFor();
            System.out.println(handler.getResultString());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
