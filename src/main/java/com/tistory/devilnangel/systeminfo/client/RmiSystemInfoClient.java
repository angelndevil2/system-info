package com.tistory.devilnangel.systeminfo.client;

import com.tistory.devilnangel.systeminfo.common.IRmiCpuInfo;
import com.tistory.devilnangel.systeminfo.common.IRmiUlimitInfo;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author k, Created on 16. 1. 30.
 */
@Slf4j
public class RmiSystemInfoClient {

    @Getter
    private final IRmiCpuInfo cpuInfo;
    @Getter
    private final IRmiUlimitInfo ulimitInfo;

    public RmiSystemInfoClient(String host) throws RemoteException, NotBoundException {
        cpuInfo = new RmiCpuInfo(host);
        ulimitInfo = new RmiUlimitInfo(host);
    }

}
