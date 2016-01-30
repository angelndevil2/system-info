package com.tistory.devilnangel.util;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author k, Created on 16. 1. 30.
 */
@Log4j
public class PropertiesUtil {

    public static final String Log4jProperties = "log4j.properties";
    public static final String AppProperties = "system-info.properties";
    @Setter @Getter
    private static String baseDir;
    @Setter @Getter
    private static String confDir;
    @Setter @Getter
    private static String binDir;
    private static final Properties log4jProperties = new Properties();
    @Getter
    private static final Properties properties = new Properties();

    public static void loadProperties() throws IOException {
        properties.load(new FileInputStream(confDir + File.separator + AppProperties));
    }

    public static void loadLog4jProperties() throws IOException {
        FileInputStream fs = new FileInputStream(confDir + File.separator + Log4jProperties);
        log4jProperties.load(fs);
    }

    private static void setDirs() {
        if (baseDir == null) baseDir = ".";
        confDir = baseDir+File.separator+File.separator+"conf";
        binDir = baseDir+File.separator+File.separator+"bin";
    }

    public static void setDirs(@NonNull String bd) {
        baseDir = bd;
        setDirs();
    }

    public static int getRimServerPort() {
        return Integer.valueOf(properties.getProperty("rmi.server.port", "1099"));
    }

    static {
        try {
            baseDir = new File("..").getCanonicalPath();
        } catch (IOException e) {
            log.warn("base directory is not set", e);
        }
        setDirs();
    }
}
