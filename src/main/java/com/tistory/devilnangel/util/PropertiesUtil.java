package com.tistory.devilnangel.util;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * @author k, Created on 16. 1. 30.
 */
@Slf4j
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
        reloadLogger();
    }

    private static void setDirs() throws IOException {
        if (baseDir == null) baseDir = ".";
        confDir = baseDir+File.separator+"conf";
        binDir = baseDir+File.separator+"bin";

        loadProperties();
        if (Boolean.valueOf(properties.getProperty("logback.use"))) loadLogbackConfiguration();
    }

    public static void setDirs(@NonNull String bd) throws IOException {
        baseDir = bd;
        setDirs();
    }

    public static void reloadLogger() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        ContextInitializer ci = new ContextInitializer(loggerContext);
        URL url = ci.findURLOfDefaultConfigurationFile(true);

        try {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(loggerContext);
            loggerContext.reset();
            configurator.doConfigure(url);
        } catch (JoranException je) {
            // StatusPrinter will handle this
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
    }

    public static int getRimServerPort() {
        return Integer.valueOf(properties.getProperty("rmi.server.port", "1099"));
    }
}
