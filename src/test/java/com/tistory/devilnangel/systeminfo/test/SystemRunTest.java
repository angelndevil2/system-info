package com.tistory.devilnangel.systeminfo.test;

import com.tistory.devilnangel.systeminfo.util.SystemRun;
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
            if (handler.getExitValue() != 0) {
                System.out.println("exit with :" + handler.getException().toString());
            } else {
                System.out.println(handler.getResultString());
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
