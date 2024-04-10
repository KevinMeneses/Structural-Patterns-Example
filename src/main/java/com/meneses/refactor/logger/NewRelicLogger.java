package com.meneses.refactor.logger;

public class NewRelicLogger {
    public void logEvent(String event, String value) {
        System.out.println("Sending " + event + ":" + value + " to NewRelic");
    }
}
