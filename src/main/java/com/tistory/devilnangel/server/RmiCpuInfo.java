package com.tistory.devilnangel.server;

import com.tistory.devilnangel.common.IRmiCpuInfo;
import com.tistory.devilnangel.system.SystemInfo;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.hyperic.sigar.SigarException;

import java.rmi.RemoteException;

/**
 * @author k, Created on 16. 1. 30.
 */
@Data
@Log4j
public class RmiCpuInfo implements IRmiCpuInfo {

    @Override
    public String getCpuInfo() throws RemoteException {
        try {
            return SystemInfo.getCpuInfo();
        } catch (SigarException e) {
            log.error(e);
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
            log.error(e);

            throw new RemoteException("cpu busy sigar exception",e);
        }
    }
}
