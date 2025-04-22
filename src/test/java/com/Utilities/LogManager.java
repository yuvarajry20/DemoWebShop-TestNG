package com.Utilities;
import org.apache.logging.log4j.Logger;

public class LogManager {
    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(LogManager.class);
    
    public static void logInfo(String message) {
        logger.info(message);
    }
    
    public static void logError(String message) {
        logger.error(message);
    }
    
    public static void logWarning(String message) {
        logger.warn(message);
    }
}