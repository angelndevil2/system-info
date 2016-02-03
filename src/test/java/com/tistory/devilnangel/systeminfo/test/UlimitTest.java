package com.tistory.devilnangel.systeminfo.test;

import com.tistory.devilnangel.systeminfo.system.Ulimit;
import org.hyperic.sigar.SigarException;
import org.junit.Test;

import java.rmi.RemoteException;

/**
 * @author k, Created on 16. 1. 24.
 */
public class UlimitTest {

    private Ulimit ulimit_;

    public UlimitTest() throws SigarException, RemoteException {
       ulimit_ = new Ulimit();
    }

    @Test
    public void OpenFilesHardLimit() {
        System.out.println("max open files hard limit = " + ulimit_.getOpenFilesHardLimit());
    }

    @Test
    public void UserProcessHardLimit() {
        System.out.println("max user process hard limit = " + ulimit_.getUserProcessHardLimit());
    }

    @Test
    public void StackSizeHardLimit() {
        System.out.println("max stack size hard limit = " + ulimit_.getStackSizeHardLimit());
    }

    @Test
    public void OpenFilesSoftLimit() {
        System.out.println("max open files soft limit = " + ulimit_.getOpenFilesSoftLimit());
    }

    @Test
    public void UserProcessSoftLimit() {
        System.out.println("max user process soft limit = " + ulimit_.getUserProcessSoftLimit());
    }

    @Test
    public void StackSizeSoftLimit() {
        System.out.println("max stack size soft limit = " + ulimit_.getStackSizeSoftLimit());
    }
}
