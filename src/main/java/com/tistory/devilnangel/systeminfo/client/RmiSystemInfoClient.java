package com.tistory.devilnangel.systeminfo.client;

import com.tistory.devilnangel.systeminfo.common.IRmiCpuInfo;
import com.tistory.devilnangel.systeminfo.common.IRmiUlimitInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author k, Created on 16. 1. 30.
 */
@Data
@Slf4j
public class RmiSystemInfoClient {

    private final IRmiCpuInfo cpuInfo;
    private final IRmiUlimitInfo ulimitInfo;

    public RmiSystemInfoClient(String host) throws RemoteException, NotBoundException {
        cpuInfo = new RmiCpuInfo(host);
        ulimitInfo = new RmiUlimitInfo(host);
    }

}
