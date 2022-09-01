package com.java.feature.thread.basic;

class Task implements Runnable {

    private final int n;
    private final Print print;

    Task(int n, boolean isOdd) {
        this.n = n;
        this.print = new Print(isOdd);
    }

    @Override
    public void run() {
        int c=0;
        while (c++ < n) {
           if (c%2 == 0) {
               print.printEvenNumbers(c);
           } else {
               print.printOddNumbers(c);
           }
        }
    }
}

class Print {

    private volatile boolean isOdd;

    Print (boolean isOdd) {
        this.isOdd = isOdd;
    }
    synchronized void printOddNumbers(int n) {
        while (isOdd) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(n);
        isOdd = true;
        notify();
    }

    synchronized void printEvenNumbers(int n) {
        while (!isOdd) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(n);
        isOdd = false;
        notify();
    }
}

public class PrintEvenOddNumbers {
    public static void main(String[] args) {
        Thread odd = new Thread(new Task(10, true));
        Thread even = new Thread(new Task(10, false));
        odd.start();
        even.start();
    }
}
