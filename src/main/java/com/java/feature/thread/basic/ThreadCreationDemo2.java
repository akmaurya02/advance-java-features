package com.java.feature.thread.basic;

class WorkerThread2 extends Thread {

    @Override
    public void run() {
        System.out.println("Executing thread - "+ Thread.currentThread().getName());
    }
}

public class ThreadCreationDemo2 {
    public static void main(String[] args) {
        System.out.println("Executing thread - "+ Thread.currentThread().getName());
        WorkerThread2 workerThread2 = new WorkerThread2();
        workerThread2.setName("WorkerThread2");
        workerThread2.start();
    }
}
