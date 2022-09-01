package com.java.feature.thread.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IoIntensiveTaskDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new IoIntensiveTask());
        }
        executorService.shutdown();
    }
}

class IoIntensiveTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Executing task by thread :: "+ Thread.currentThread().getName());
    }
}
