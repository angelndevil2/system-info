package com.tistory.devilnangel.systeminfo.server;

import com.tistory.devilnangel.systeminfo.common.IRmiCpuInfo;
import com.tistory.devilnangel.systeminfo.system.SystemInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hyperic.sigar.SigarException;

import java.rmi.RemoteException;

/**
 * @author k, Created on 16. 1. 30.
 */
@Data
@Slf4j
public class RmiCpuInfo implements IRmiCpuInfo {

    @Override
    public String getCpuInfo() throws RemoteException {
        try {
            return SystemInfo.getCpuInfo();
        } catch (SigarException e) {
            log.error("cpu info sigar exception", e);
            throw new RemoteException("cpu info sigar exception",e);
        }
    }

    /**
     * @return %busy
     */
    @Override
    public double getCpuBusy()  throws RemoteException {
        try {
            return SystemInfo.getCpuBusy();
        } catch (SigarException e) {
            log.error("cpu busy sigar exception", e);

            throw new RemoteException("cpu busy sigar exception",e);
        }
    }
}
