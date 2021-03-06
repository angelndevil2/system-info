package com.tistory.devilnangel.systeminfo.system;

import com.tistory.devilnangel.systeminfo.common.Unit;
import com.tistory.devilnangel.systeminfo.util.UnitTranasform;
import com.tistory.devilnangel.systeminfo.util.SigarInstances;
import lombok.Data;
import org.hyperic.sigar.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Gather Os, Memory information
 *
 * @author k, Created on 16. 1. 19.
 */
@Data
public class SystemInfo {

    public static final String OS_NAME = "Os Name";
    public static final String CPU_INFO = "CPU Info";
    public static final String MEM_INFO = "Mem Info";

    private static final Sigar sigar_ = SigarInstances.SIGAR;
    private static final OperatingSystem os_ = OperatingSystem.getInstance();
    private static final SigarProxy sigarProxy = SigarInstances.SIGAR_PROXY;

    /**
     *
     * @return String of
     * {@link OperatingSystem#getName()} +
     * {@link OperatingSystem#getVersion()} +
     * {@link OperatingSystem#getVendor()} +
     * {@link OperatingSystem#getVendorVersion()}
     * <br />
     * ex. Linux 3.10.0-327.3.1.el7.x86_64 (CentOS 7.2.1511)
     */
    public static String getOsName() {
        StringBuilder sb = new StringBuilder();

        os_.isWin32("aa");
        sb.append(os_.getName());
        sb.append(" ");
        sb.append(os_.getVersion());
        sb.append(" (");
        sb.append(os_.getVendor()); // ex. vendor = CentOs, vendorName = linux
        sb.append(" ");
        sb.append(os_.getVendorVersion());
        sb.append(")");
        return sb.toString();
    }

    /**
     * Assume all cpu is same.
     *
     * @return String of
     * {@link CpuInfo#getVendor()} +
     * {@link CpuInfo#getModel()} ()} +
     * {@link CpuInfo#getTotalCores()}
     * <br />
     *
     * ex. Intel Core(TM) i7-4930K CPU @ 3.40GHz X 12
     *
     * @throws SigarException
     */
    public static String getCpuInfo() throws SigarException {
        CpuInfo[] cpus = sigar_.getCpuInfoList();

        for (CpuInfo ci : cpus) {
            StringBuilder sb = new StringBuilder();
            sb.append(ci.getVendor());
            sb.append(" ");
            sb.append(ci.getModel());
            sb.append(" X ");
            sb.append(ci.getTotalCores());

            return sb.toString();
        }

        throw new SigarException("Cpu count is 0");
    }

    /**
     *
     * @return %busy
     */
    public static double getCpuBusy() throws SigarException {
        return 100 - sigarProxy.getCpuPerc().getIdle()*100;
    }

    /**
     *
     * @param unit KB, MB, GB
     * @return total memory size string with unit
     * @throws SigarException
     */
    public static String getMemInfo(Unit unit) throws SigarException {

        Mem mem = sigar_.getMem();

        StringBuilder sb = new StringBuilder();
        sb.append(UnitTranasform.transformLong(mem.getTotal(), unit));
        if (!Unit.NONE.equals(unit)) sb.append(unit.toString());

        return sb.toString();
    }

    /**
     *
     * @return true if window os
     */
    public static boolean isWindow() {
        return OperatingSystem.IS_WIN32;
    }

    public static void main(String[] args) throws RemoteException, NotBoundException, SigarException {

        StringBuilder sb = new StringBuilder();

        sb.append(OS_NAME);
        sb.append(" : ");
        sb.append(SystemInfo.getOsName());
        sb.append('\n');

        sb.append(CPU_INFO);
        sb.append(" : ");
        try {
            sb.append(SystemInfo.getCpuInfo());
        } catch (SigarException e) {
            sb.append("null");
        }
        sb.append('\n');

        sb.append(MEM_INFO);
        sb.append(" : ");
        try {
            sb.append(SystemInfo.getMemInfo(Unit.GB));
        } catch (SigarException e) {
            sb.append("null");
        }

        System.out.println(sb);

    }
}
