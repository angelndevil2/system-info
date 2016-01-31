package com.tistory.devilnangel.test;

import com.tistory.devilnangel.client.RmiSystemInfoClient;
import com.tistory.devilnangel.common.Unit;
import com.tistory.devilnangel.server.RmiSystemInfoServer;
import com.tistory.devilnangel.system.SystemInfo;
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
    public void RMIServerInfoTest() throws RemoteException {
        RmiSystemInfoServer s = new RmiSystemInfoServer();

        s.main(null);
    }

    @Test
    public void RMIClientTest() throws RemoteException, NotBoundException {
        RmiSystemInfoClient sic = new RmiSystemInfoClient("localhost");
        System.out.println(sic.getCpuInfo().getCpuBusy());
    }
}
