package com.tistory.devilnangel.systeminfo.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author k, Created on 16. 2. 3.
 */
public interface IRmiUlimitInfo extends Remote {

    /**
     * @return open file hard limit
     */
    long getOpenFilesHardLimit() throws RemoteException;

    /**
     * @return max user process hard limit
     */
    long getUserProcessHardLimit() throws RemoteException;

    /**
     *
     * @return max stack size hard limit, -1 = unlimited
     */
    long getStackSizeHardLimit() throws RemoteException;

    /**
     * @return open file soft limit
     */
    long getOpenFilesSoftLimit() throws RemoteException;

    /**
     * @return max user process soft limit
     */
    long getUserProcessSoftLimit() throws RemoteException;

    /**
     *
     * @return max stack size soft limit, -1 = unlimited
     */
    long getStackSizeSoftLimit() throws RemoteException;
}
