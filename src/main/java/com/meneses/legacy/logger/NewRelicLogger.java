package com.meneses.legacy.logger;

public class NewRelicLogger {
    public void logEvent(String event, String value) {
        System.out.println("Sending " + event + ":" + value + " to NewRelic");
    }
}
