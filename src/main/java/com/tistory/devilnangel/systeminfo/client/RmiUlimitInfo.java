package com.tistory.devilnangel.systeminfo.client;

import com.tistory.devilnangel.systeminfo.common.IRmiUlimitInfo;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author k, Created on 16. 2. 3.
 */
public class RmiUlimitInfo implements IRmiUlimitInfo {

    private final IRmiUlimitInfo ulimitInfo;

    public RmiUlimitInfo(String host) throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry(host);
        ulimitInfo = (IRmiUlimitInfo) registry.lookup(com.tistory.devilnangel.systeminfo.system.Ulimit.class.getSimpleName());
    }
    /**
     * @return open file hard limit
     */
    @Override
    public long getOpenFilesHardLimit() throws RemoteException {
        return ulimitInfo.getOpenFilesHardLimit();
    }

    /**
     * @return max user process hard limit
     */
    @Override
    public long getUserProcessHardLimit() throws RemoteException {
        return ulimitInfo.getUserProcessHardLimit();
    }

    /**
     * @return max stack size hard limit, -1 = unlimited
     */
    @Override
    public long getStackSizeHardLimit() throws RemoteException {
        return ulimitInfo.getStackSizeHardLimit();
    }

    /**
     * @return open file soft limit
     */
    @Override
    public long getOpenFilesSoftLimit() throws RemoteException {
        return ulimitInfo.getOpenFilesSoftLimit();
    }

    /**
     * @return max user process soft limit
     */
    @Override
    public long getUserProcessSoftLimit() throws RemoteException {
        return ulimitInfo.getUserProcessSoftLimit();
    }

    /**
     * @return max stack size soft limit, -1 = unlimited
     */
    @Override
    public long getStackSizeSoftLimit() throws RemoteException {
        return ulimitInfo.getStackSizeSoftLimit();
    }
}
