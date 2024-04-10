package com.meneses.refactor.logger;

public class DataDogLogger {
    public void logEvent(String event, String value) {
        System.out.println("Sending " + event + ":" + value + " to DataDog");
    }
}
