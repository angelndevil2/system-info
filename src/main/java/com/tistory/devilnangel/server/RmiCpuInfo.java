package com.tistory.devilnangel.server;

import com.tistory.devilnangel.common.IRmiCpuInfo;
import com.tistory.devilnangel.system.SystemInfo;
import lombok.Data;
import org.hyperic.sigar.SigarException;

/**
 * @author k, Created on 16. 1. 30.
 */
@Data
public class RmiCpuInfo implements IRmiCpuInfo {

    @Override
    public String getCpuInfo() throws SigarException {
        return SystemInfo.getCpuInfo();
    }

    /**
     * @return %busy
     */
    @Override
    public double getCpuBusy()  throws SigarException {
        return SystemInfo.getCpuBusy();
    }
}
