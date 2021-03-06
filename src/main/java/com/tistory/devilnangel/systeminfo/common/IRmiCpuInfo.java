package com.tistory.devilnangel.systeminfo.common;

import org.hyperic.sigar.CpuInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author k, Created on 16. 1. 30.
 */
public interface IRmiCpuInfo extends Remote {

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
     * @throws RemoteException
     */
    String getCpuInfo() throws RemoteException;

    /**
     *
     * @return %busy
     * @throws RemoteException
     */
    double getCpuBusy() throws RemoteException;
}
