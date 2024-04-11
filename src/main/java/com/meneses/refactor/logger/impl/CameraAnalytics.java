package com.meneses.refactor.logger.impl;

import com.meneses.refactor.logger.Logger;

import java.util.ArrayList;

public class CameraAnalytics implements Logger {
    private final ArrayList<Logger> loggers = new ArrayList<>();

    @Override
    public void logEvent(String event, String value) {
        for (Logger logger : loggers) {
            logger.logEvent(event, value);
        }
    }

    public void addLogger(Logger logger) {
        loggers.add(logger);
    }
}
