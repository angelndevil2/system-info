package com.tistory.devilnangel.test;

import com.tistory.devilnangel.common.Ulimit;
import org.junit.Test;

import java.io.IOException;

/**
 * @author k, Created on 16. 1. 24.
 */
public class UlimitTest {

    private Ulimit ulimit_ = new Ulimit();

    @Test
    public void OpenFiles() throws IOException, InterruptedException {
        System.out.println("open files = "+ulimit_.getOpenFiles());
    }
}
