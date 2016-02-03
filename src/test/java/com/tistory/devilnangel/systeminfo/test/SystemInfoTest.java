package com.tistory.devilnangel.systeminfo.test;

import com.tistory.devilnangel.systeminfo.client.RmiSystemInfoClient;
import com.tistory.devilnangel.systeminfo.common.Unit;
import com.tistory.devilnangel.systeminfo.server.RmiSystemInfoServer;
import com.tistory.devilnangel.systeminfo.system.SystemInfo;
import org.hyperic.sigar.SigarException;
import org.junit.Test;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author k, Created on 16. 1. 19.
 */
public class SystemInfoTest {

    @Test
    public void getOsName() {
        System.out.println(SystemInfo.getOsName());
    }

    @Test
    public void getCpuInfo() throws SigarException {
        System.out.println(SystemInfo.getCpuInfo());
    }

    @Test
    public void getMemInfo() throws SigarException {
        System.out.println(SystemInfo.getMemInfo(Unit.GB));
    }

    @Test
    public void isWindow() {
        System.out.println(SystemInfo.isWindow());
    }

    @Test
    public void RMITest() throws RemoteException, SigarException, NotBoundException {
        RmiSystemInfoServer s = new RmiSystemInfoServer();
        System.setProperty("java.rmi.server.hostname", "localhost");
        s.startRmiServer();

        RmiSystemInfoClient sic = new RmiSystemInfoClient("localhost");
        System.out.println(sic.getCpuInfo().getCpuBusy());

        System.out.println(sic.getUlimitInfo().getOpenFilesHardLimit());
    }
}
