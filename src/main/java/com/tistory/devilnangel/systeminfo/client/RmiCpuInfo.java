package com.tistory.devilnangel.systeminfo.client;

import com.tistory.devilnangel.systeminfo.common.IRmiCpuInfo;

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
        cpuInfo = (IRmiCpuInfo) registry.lookup(IRmiCpuInfo.class.getCanonicalName());
    }

    @Override
    public String getCpuInfo() throws RemoteException {
        return cpuInfo.getCpuInfo();
    }

    /**
     * @return %busy
     */
    @Override
    public double getCpuBusy() throws RemoteException {
        return cpuInfo.getCpuBusy();
    }
}
