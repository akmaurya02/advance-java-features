package com.java.feature.outofmemoryerror;

/**
 * Run this program with jvm option
 * -Xmx32m
 */
public class HeapSpaceErrorTestApp {

    public static void main(String args[]) {
        Integer[] array = new Integer[1000*1000*100];
    }
}
