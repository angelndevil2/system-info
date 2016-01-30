package com.tistory.devilnangel.client;

import com.tistory.devilnangel.common.IRmiCpuInfo;
import org.hyperic.sigar.SigarException;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author k, Created on 16. 1. 30.
 */
public class RmiCpuInfo  implements IRmiCpuInfo {

    private final IRmiCpuInfo cpuInfo;

    public RmiCpuInfo(String host) throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry(host);
        cpuInfo = (IRmiCpuInfo) registry.lookup(com.tistory.devilnangel.server.RmiCpuInfo.class.getSimpleName());
    }

    @Override
    public String getCpuInfo() throws SigarException {
        return cpuInfo.getCpuInfo();
    }

    /**
     * @return %busy
     */
    @Override
    public double getCpuBusy() throws SigarException {
        return cpuInfo.getCpuBusy();
    }
}
