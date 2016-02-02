package com.tistory.devilnangel.systeminfo.client;

import com.tistory.devilnangel.systeminfo.common.IRmiCpuInfo;
import lombok.Data;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author k, Created on 16. 1. 30.
 */
@Data
public class RmiSystemInfoClient {

    private final IRmiCpuInfo cpuInfo;

    public RmiSystemInfoClient(String host) throws RemoteException, NotBoundException {
        cpuInfo = new RmiCpuInfo(host);
    }

}
