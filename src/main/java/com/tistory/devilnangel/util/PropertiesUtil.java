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

    public static final String LogbackConfig = "logback.xml";
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

    private static void loadProperties() throws IOException {
        properties.load(new FileInputStream(confDir + File.separator + AppProperties));
    }

    private static void loadLogbackConfiguration() {
        System.setProperty("logback.configurationFile", confDir+File.separator+LogbackConfig);
    }

    private static void setDirs() throws IOException {
        if (baseDir == null) baseDir = ".";
        confDir = baseDir+File.separator+File.separator+"conf";
        binDir = baseDir+File.separator+File.separator+"bin";

        loadProperties();
        if (Boolean.valueOf(properties.getProperty("logback.use"))) loadLogbackConfiguration();
    }

    public static void setDirs(@NonNull String bd) throws IOException {
        baseDir = bd;
        setDirs();
    }

    public static int getRimServerPort() {
        return Integer.valueOf(properties.getProperty("rmi.server.port", "1099"));
    }
}
