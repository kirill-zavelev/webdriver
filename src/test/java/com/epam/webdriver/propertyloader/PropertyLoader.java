package com.epam.webdriver.propertyloader;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    private static final String PROPERTY_FILE_PATH = "src/test/resources/mail.properties";

    public static String loadProperty(String propertyKey) {
        Properties properties = new Properties();
        BufferedInputStream stream;
        try {
            stream = new BufferedInputStream(new FileInputStream(PROPERTY_FILE_PATH));
            properties.load(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(propertyKey);
    }
}
