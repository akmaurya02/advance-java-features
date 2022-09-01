package com.java.feature.thread.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CpuIntensiveTaskDemo {
    public static void main(String[] args) {
        // Get the available cores
        int availableCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available cores - "+ availableCores);
        ExecutorService executorService = Executors.newFixedThreadPool(availableCores);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new CpuIntensiveTask());
        }
        executorService.shutdown();
    }
}

class CpuIntensiveTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Executing task by thread :: "+ Thread.currentThread().getName());
    }
}
