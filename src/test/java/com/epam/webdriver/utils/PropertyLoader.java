package com.epam.webdriver.utils;

import java.util.ResourceBundle;

public class PropertyLoader {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    public static String loadProperty(String key){
        return resourceBundle.getString(key);
    }
}
