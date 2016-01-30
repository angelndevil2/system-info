package com.tistory.devilnangel.server;

import com.tistory.devilnangel.common.IRmiCPUInfo;
import com.tistory.devilnangel.system.SystemInfo;
import com.tistory.devilnangel.util.PropertiesUtil;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.hyperic.sigar.SigarException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author k, Created on 16. 1. 30.
 */
@Log4j
public @Data
class RmiSystemInfoServer implements IRmiCPUInfo, Runnable {

    private int port;

    public RmiSystemInfoServer() {
        try {
            port = PropertiesUtil.getRimServerPort();
        } catch (Throwable t) {
            log.warn("rmi.server.port property is malformed");
            port = 1099;
        }
    }

    public RmiSystemInfoServer(int port) {
        this.port = port;
    }

    @Override
    public String getCPUInfo() throws RemoteException, SigarException {
        return SystemInfo.getCpuInfo();
    }

    public void startRmiServer() throws RemoteException {

        try {
            // export to java runtime
            IRmiCPUInfo rmiCPUInfo = (IRmiCPUInfo)UnicastRemoteObject.exportObject(this, 0);

            InetAddress localHost=null;
            // Bug 47980 - allow override of local hostname
            String host = System.getProperties().getProperty("java.rmi.server.hostname"); // $NON-NLS-1$
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
                        + "\tCan be overridden by defining the system property 'java.rmi.server.hostname' - see jmeter-server script file");
            }

            try {
                LocateRegistry.createRegistry(port);
            } catch (RemoteException e){
                String msg="Problem creating registry: "+e;
                log.warn(msg);
                System.err.println(msg);
                System.err.println("Continuing...");
            }

            // bind the remote object in the registry
            Registry registry = LocateRegistry.getRegistry(port); // use 1099
            registry.bind(this.getClass().getSimpleName(), rmiCPUInfo);

            System.out.println("server started.");

        } catch (Exception ex) {
            // Throw an Exception to ensure caller knows ...
            throw new RemoteException("Cannot start. ", ex);
        }
    }


    public static void main(String[] args) throws RemoteException {

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
