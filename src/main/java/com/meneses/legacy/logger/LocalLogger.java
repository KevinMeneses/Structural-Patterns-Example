package com.meneses.legacy.logger;

public class LocalLogger {
    public void logEvent(String event, String value) {
        System.out.println("Saving " + event + ":" + value + " in local file");
    }
}
