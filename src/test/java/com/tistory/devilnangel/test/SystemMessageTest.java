package com.tistory.devilnangel.test;

import com.tistory.devilnangel.common.SystemMessages;
import org.junit.Test;

/**
 * @author k, Created on 16. 1. 30.
 */
public class SystemMessageTest {

    @Test
    public void printNullServerConnection() {
        System.out.println(SystemMessages.NULL_MBEAN_SERVER_CONNECTION);
    }
}
