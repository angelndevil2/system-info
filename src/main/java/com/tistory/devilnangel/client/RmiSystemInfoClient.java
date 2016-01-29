package com.tistory.devilnangel.client;

import com.tistory.devilnangel.common.IRmiCPUInfo;
import com.tistory.devilnangel.server.RmiSystemInfoServer;
import lombok.Data;
import org.hyperic.sigar.SigarException;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author k, Created on 16. 1. 30.
 */
@Data
public class RmiSystemInfoClient {

    public String getCpuInfo(String host) throws RemoteException, NotBoundException, SigarException {

        Registry registry = LocateRegistry.getRegistry(host);
        IRmiCPUInfo rmiCPUInfo = (IRmiCPUInfo) registry.lookup(RmiSystemInfoServer.class.getSimpleName());

        return rmiCPUInfo.getCPUInfo();
    }
}
