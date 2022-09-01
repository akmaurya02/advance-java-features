package com.java.feature.thread.executors;

public class Task implements Runnable{
    @Override
    public void run() {
        System.out.println("Executing task by thread :: "+Thread.currentThread().getName());
    }
}
