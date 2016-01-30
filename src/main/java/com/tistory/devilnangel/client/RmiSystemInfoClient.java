package com.tistory.devilnangel.client;

import com.tistory.devilnangel.common.IRmiCpuInfo;
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
        cpuInfo = new com.tistory.devilnangel.client.RmiCpuInfo(host);
    }

}
