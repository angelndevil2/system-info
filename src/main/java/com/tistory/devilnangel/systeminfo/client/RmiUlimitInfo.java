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
    public long getMaxOpenFilesHardLimit() throws RemoteException {
        return ulimitInfo.getMaxOpenFilesHardLimit();
    }

    /**
     * @return max user process hard limit
     */
    @Override
    public long getMaxUserProcessHardLimit() throws RemoteException {
        return ulimitInfo.getMaxUserProcessHardLimit();
    }

    /**
     * @return max stack size hard limit, -1 = unlimited
     */
    @Override
    public long getMaxStackSizeHardLimit() throws RemoteException {
        return ulimitInfo.getMaxStackSizeHardLimit();
    }

    /**
     * @return open file soft limit
     */
    @Override
    public long getMaxOpenFilesSoftLimit() throws RemoteException {
        return ulimitInfo.getMaxOpenFilesSoftLimit();
    }

    /**
     * @return max user process soft limit
     */
    @Override
    public long getMaxUserProcessSoftLimit() throws RemoteException {
        return ulimitInfo.getMaxUserProcessSoftLimit();
    }

    /**
     * @return max stack size soft limit, -1 = unlimited
     */
    @Override
    public long getMaxStackSizeSoftLimit() throws RemoteException {
        return ulimitInfo.getMaxStackSizeSoftLimit();
    }
}
