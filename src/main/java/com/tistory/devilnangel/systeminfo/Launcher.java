package com.tistory.devilnangel.systeminfo;

import com.tistory.devilnangel.systeminfo.common.Unit;
import com.tistory.devilnangel.systeminfo.server.RmiSystemInfoServer;
import com.tistory.devilnangel.systeminfo.system.SystemInfo;
import com.tistory.devilnangel.systeminfo.util.PropertiesUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.hyperic.sigar.SigarException;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import static java.lang.System.exit;

/**
 * @author k, Created on 16. 1. 30.
 */
@Data
@Slf4j
public class Launcher {

    private static final CmdOptions options = new CmdOptions();

    public static void main(String[] args) throws ParseException, SigarException, IOException {

        options.setArgs(args);
        CommandLine cmd = options.getCmd();

        if (cmd.hasOption("h")) {
            options.printUsage();
            return;
        }

        if (cmd.hasOption("c")) System.out.println(SystemInfo.getCpuInfo());
        if (cmd.hasOption("m")) System.out.println(SystemInfo.getMemInfo(Unit.GB));
        if (cmd.hasOption("o")) System.out.println(SystemInfo.getOsName());

        if (cmd.hasOption("d")) {
            try {

                PropertiesUtil.setDirs(cmd.getOptionValue("d").trim());

            } catch (IOException e) {

                System.err.println(PropertiesUtil.getConfDir() + File.separator + PropertiesUtil.AppProperties + " not found. may use -d option" + e);
                return;
            }
        }

        if (cmd.hasOption("s")) {

            RmiSystemInfoServer rmiSystemInfoServer;

            if (cmd.hasOption("p")) {

                try {
                    int port = Integer.valueOf(cmd.getOptionValue("p"));
                    rmiSystemInfoServer = new RmiSystemInfoServer(port);
                } catch (Throwable t) {
                    System.err.println(cmd.getOptionValue("p") + " is malformed. try default value");
                    rmiSystemInfoServer = new RmiSystemInfoServer();
                }

            } else {
                rmiSystemInfoServer = new RmiSystemInfoServer();
            }

            try {

                rmiSystemInfoServer.startRmiServer();

            } catch (RemoteException e) {
                exit(-1);
            }

        }
    }
}
