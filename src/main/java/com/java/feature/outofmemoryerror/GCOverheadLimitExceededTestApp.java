package com.java.feature.outofmemoryerror;

import java.util.Map;
import java.util.Random;

/**
 * Run this program with jvm option
 * -Xmx100m -XX:+UseParallelGC
 */
public class GCOverheadLimitExceededTestApp {
    public static void main(String args[]) {
        Map m;
        m = System.getProperties();
        Random r = new Random();
        while (true) {
            m.put(r.nextInt(), "randomValue");
        }
    }
}
