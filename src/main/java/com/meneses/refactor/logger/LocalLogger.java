package com.meneses.refactor.logger;

public class LocalLogger {
    public void logEvent(String event, String value) {
        System.out.println("Saving " + event + ":" + value + " in local file");
    }
}
