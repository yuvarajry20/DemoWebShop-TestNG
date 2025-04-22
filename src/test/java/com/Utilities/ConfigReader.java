package com.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    
    static {
        try {
            String configPath = "src/test/resources/config.properties";
            FileInputStream input = new FileInputStream(configPath);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties");
        }
    }
    
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}