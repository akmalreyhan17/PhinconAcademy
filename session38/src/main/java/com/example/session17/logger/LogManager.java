package com.example.session17.logger;

public class LogManager {
    private Logger logger;

    public LogManager(Logger logger) {
        this.logger = logger;
    }

    public void logMessage(String message) {
        logger.log(message);
    }
}
