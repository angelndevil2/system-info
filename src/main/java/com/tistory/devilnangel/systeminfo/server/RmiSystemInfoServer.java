package com.tistory.devilnangel.systeminfo.server;

import com.tistory.devilnangel.systeminfo.common.IRmiCpuInfo;
import com.tistory.devilnangel.systeminfo.common.IRmiUlimitInfo;
import com.tistory.devilnangel.systeminfo.system.Ulimit;
import com.tistory.devilnangel.systeminfo.util.PropertiesUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hyperic.sigar.SigarException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author k, Created on 16. 1. 30.
 */
@Slf4j
public @Data
class RmiSystemInfoServer implements Runnable {

    private int port;
    private RmiCpuInfo rmiCpuInfo = new RmiCpuInfo();
    private final Ulimit ulimitInfo;

    public RmiSystemInfoServer() throws SigarException, RemoteException {
        try {
            port = PropertiesUtil.getRimServerPort();
        } catch (Throwable t) {
            log.warn("rmi.server.port property is malformed");
            port = 1099;
        }

        ulimitInfo = new Ulimit();
    }

    /**
     *
     * @param port rmi port to use
     */
    public RmiSystemInfoServer(int port) throws SigarException, RemoteException {
        this.port = port;
        ulimitInfo = new Ulimit();
    }

    /**
     * @throws RemoteException
     * @throws AlreadyBoundException
     */
    private void registerCpuInfo() throws RemoteException, AlreadyBoundException {
        // export to java runtime
        IRmiCpuInfo cpuInfo = (IRmiCpuInfo)UnicastRemoteObject.exportObject(rmiCpuInfo, 0);
        // bind the remote object in the registry
        Registry registry = LocateRegistry.getRegistry(port); // use 1099
        registry.bind(RmiCpuInfo.class.getSimpleName(), cpuInfo);
    }

    /**
     * @throws RemoteException
     * @throws AlreadyBoundException
     */
    private void registerUlimitInfo() throws RemoteException, AlreadyBoundException {
        // export to java runtime
        IRmiUlimitInfo ulimitInfo = (IRmiUlimitInfo)UnicastRemoteObject.exportObject(this.ulimitInfo, 0);
        // bind the remote object in the registry
        Registry registry = LocateRegistry.getRegistry(port); // use 1099
        registry.bind(Ulimit.class.getSimpleName(), ulimitInfo);
    }

    /**
     *
     * @throws RemoteException
     */
    public void startRmiServer() throws RemoteException {

        try {

            InetAddress localHost=null;
            // Bug 47980 - allow override of local hostname
            String host = System.getProperty("java.rmi.server.hostname"); // $NON-NLS-1$
            try {
                if( host==null ) {
                    log.info("System property 'java.rmi.server.hostname' is not defined, using localHost address");
                    localHost = InetAddress.getLocalHost();
                } else {
                    log.info("Resolving by name the value of System property 'java.rmi.server.hostname':"+host);
                    localHost = InetAddress.getByName(host);
                }
            } catch (UnknownHostException e1) {
                throw new RemoteException("Cannot start. Unable to get local host IP address.", e1);
            }
            log.info("Local IP address="+localHost.getHostAddress());
            // BUG 52469 : Allow loopback address for SSH Tunneling of RMI traffic
            if (host == null && localHost.isLoopbackAddress()){
                String hostName = localHost.getHostName();
                throw new RemoteException("Cannot start. "+hostName+" is a loopback address.");
            }
            if (localHost.isSiteLocalAddress()){
                // should perhaps be log.warn, but this causes the client-server test to fail
                log.info("IP address is a site-local address; this may cause problems with remote access.\n"
                        + "\tCan be overridden by defining the system property 'java.rmi.server.hostname'");
            }

            try {
                LocateRegistry.createRegistry(port);
            } catch (RemoteException e){
                String msg="Problem creating registry: "+e;
                log.warn(msg);
                System.err.println(msg);
                System.err.println("Continuing...");
            }

            registerCpuInfo();
            registerUlimitInfo();
            System.out.println("server started.");
        } catch (Exception ex) {
            // Throw an Exception to ensure caller knows ...
            throw new RemoteException("Cannot start. ", ex);
        }
    }

    public void stopServer() {

    }

    public static void main(String[] args) throws RemoteException, SigarException {

        RmiSystemInfoServer obj = new RmiSystemInfoServer();

        obj.startRmiServer();
    }

    @Override
    public void run() {
        try {
            startRmiServer();
        } catch (RemoteException e) {

            // error log already send.
            return;
        }
    }
}
