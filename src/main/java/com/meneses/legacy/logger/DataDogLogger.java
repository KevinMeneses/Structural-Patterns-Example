package com.meneses.legacy.logger;

public class DataDogLogger {
    public void logEvent(String event, String value) {
        System.out.println("Sending " + event + ":" + value + " to DataDog");
    }
}
