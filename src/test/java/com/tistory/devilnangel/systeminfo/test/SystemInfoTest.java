package com.tistory.devilnangel.systeminfo.test;

import com.tistory.devilnangel.systeminfo.common.Unit;
import com.tistory.devilnangel.systeminfo.system.SystemInfo;
import org.hyperic.sigar.SigarException;
import org.junit.Test;

/**
 * @author k, Created on 16. 1. 19.
 */
public class SystemInfoTest {

    @Test
    public void getOsName() {
        System.out.println(SystemInfo.getOsName());
    }

    @Test
    public void getCpuInfo() throws SigarException {
        System.out.println(SystemInfo.getCpuInfo());
    }

    @Test
    public void getMemInfo() throws SigarException {
        System.out.println(SystemInfo.getMemInfo(Unit.GB));
    }

    @Test
    public void isWindow() {
        System.out.println(SystemInfo.isWindow());
    }

}
