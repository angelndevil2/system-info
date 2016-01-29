package com.tistory.devilnangel.common;

import org.hyperic.sigar.SigarException;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author k, Created on 16. 1. 30.
 */
public interface IRmiCPUInfo extends Remote {

    String getCPUInfo() throws RemoteException, SigarException;
}
