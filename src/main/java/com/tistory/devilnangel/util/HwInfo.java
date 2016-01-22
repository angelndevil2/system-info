package com.tistory.devilnangel.util;

import org.hyperic.sigar.*;

/**
 * @author k, Created on 16. 1. 19.
 */
public class HwInfo {

    public static final String OS_NAME = "Os Name";
    public static final String CPU_INFO = "CPU Info";
    public static final String MEM_INFO = "Mem Info";

    private final Sigar sigar_ = new Sigar();
    private final OperatingSystem os_ = OperatingSystem.getInstance();

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
    public String getOsName() {
        StringBuilder sb = new StringBuilder();

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
     * {@link CpuInfo#getTotalCores())
     * <br />
     *
     * ex. Intel Core(TM) i7-4930K CPU @ 3.40GHz X 12
     *
     * @throws SigarException
     */
    public String getCpuInfo() throws SigarException {
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

        throw new SigarException("Cpu number is 0");
    }

    /**
     *
     * @return String of
     * {@link Mem#getTotal()}
     * @throws SigarException
     */
    public String getMemInfo() throws SigarException {

        Mem mem = sigar_.getMem();

        StringBuilder sb = new StringBuilder();

        sb.append(mem.getTotal());

        return sb.toString();
    }

    public static void main(String[] args) {

        HwInfo hw_info = new HwInfo();
        StringBuilder sb = new StringBuilder();

        sb.append(OS_NAME);
        sb.append(" : ");
        sb.append(hw_info.getOsName());
        sb.append('\n');

        sb.append(CPU_INFO);
        sb.append(" : ");
        try {
            sb.append(hw_info.getCpuInfo());
        } catch (SigarException e) {
            sb.append("null");
        }
        sb.append('\n');

        sb.append(MEM_INFO);
        sb.append(" : ");
        try {
            sb.append(hw_info.getMemInfo());
        } catch (SigarException e) {
            sb.append("null");
        }

        System.out.println(sb);
    }
}
