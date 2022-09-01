package com.java.feature.thread.basic;

class WorkerThread1 implements Runnable {

    @Override
    public void run() {
        System.out.println("Executing thread - "+ Thread.currentThread().getName());
    }
}

public class ThreadCreationDemo1 {
    public static void main(String[] args) {
        System.out.println("Executing thread - "+ Thread.currentThread().getName());
        WorkerThread1 workerThread1 = new WorkerThread1();
        Thread thread = new Thread(workerThread1);
        thread.setName("WorkerThread1");
        thread.start();
    }
}
