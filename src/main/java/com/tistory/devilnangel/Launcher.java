package com.tistory.devilnangel;

import com.tistory.devilnangel.common.Unit;
import com.tistory.devilnangel.server.RmiSystemInfoServer;
import com.tistory.devilnangel.system.SystemInfo;
import com.tistory.devilnangel.util.PropertiesUtil;
import lombok.Data;
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
public class Launcher {



    private static final CmdOptions options = new CmdOptions();
    private static final SystemInfo sysInfo = new SystemInfo();

    public static void main(String[] args) throws ParseException, SigarException, IOException {

        options.setArgs(args);
        CommandLine cmd = options.getCmd();

        if (cmd.hasOption("h")) {
            options.printUsage();
            return;
        }

        if (cmd.hasOption("c")) {
            System.out.println(SystemInfo.getCpuInfo());
        }

        if (cmd.hasOption("m")) {
            System.out.println(SystemInfo.getMemInfo(Unit.GB));
        }

        if (cmd.hasOption("o")) {
            System.out.println(SystemInfo.getOsName());
        }

        if (cmd.hasOption("d")) {

            PropertiesUtil.setDirs(cmd.getOptionValue("d").trim());

        }

        try {

            PropertiesUtil.loadProperties();

        } catch (IOException e) {

            System.err.println(PropertiesUtil.getConfDir()+File.separator+PropertiesUtil.AppProperties + " not found. may use -d option" + e);
            return;
        }

        try {
            PropertiesUtil.loadLog4jProperties();
        } catch (IOException e) {

            System.err.println(PropertiesUtil.getConfDir()+File.separator+PropertiesUtil.Log4jProperties + " not found. may use -d option" + e);
            return;
        }

        if (cmd.hasOption("s")) {

            RmiSystemInfoServer rmiSystemInfoServer = new RmiSystemInfoServer();

            try {

                rmiSystemInfoServer.startRmiServer();

            } catch (RemoteException e) {
                exit(-1);
            }

        }
    }
}
