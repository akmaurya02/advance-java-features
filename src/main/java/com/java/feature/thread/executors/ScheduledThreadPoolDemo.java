package com.java.feature.thread.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        // Task will run after 10 seconds of delay
        scheduledExecutorService.schedule(new Task(), 5, TimeUnit.SECONDS);

        // Task will run repeatedly in every 10 seconds
        scheduledExecutorService.scheduleAtFixedRate(new Task(), 10, 5, TimeUnit.SECONDS);

        // Task will run repeatedly in 10 seconds after previous task completes
        scheduledExecutorService.scheduleWithFixedDelay(new Task(), 10, 5, TimeUnit.SECONDS);
    }
}
