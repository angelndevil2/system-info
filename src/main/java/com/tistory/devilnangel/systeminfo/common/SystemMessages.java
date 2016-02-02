package com.tistory.devilnangel.systeminfo.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

/**
 * @author k, Created on 16. 1. 22.
 */
public class SystemMessages {

    private static Properties properties_;

    static {
        ClassLoader classLoader = SystemMessages.class.getClassLoader();
        // 프로퍼티 파일 뉴
        properties_ = new Properties();
        try {
            Class.forName("java.nio.file.Files");
            Path path = FileSystems.getDefault().getPath(classLoader.getResource("messages.properties").getPath());
            InputStream input = Files.newInputStream(path, StandardOpenOption.READ);
            properties_.load(input);
            input.close();
        } catch (ClassNotFoundException e) {
            try {
                FileInputStream fin = new FileInputStream("messages.properties");
                properties_.load(new BufferedInputStream(fin));
                fin.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public final static String NULL_MBEAN_SERVER_CONNECTION;
    static {
        NULL_MBEAN_SERVER_CONNECTION = properties_.getProperty("NULL_MBEAN_SERVER_CONNECTION");
    }
}
