package com.java.feature.lambda;

interface Cab {
    String bookCab(String source, String destination);
}

class Uber implements Cab {
    @Override
    public String bookCab(String source, String destination) {
        System.out.println("Uber cab is booked from "+ source + " to " + destination);
        return "Price: 400";
    }
}

public class LambdaExpDemo {
    public static void main(String[] args) {
        Cab cab1 = new Uber();
        cab1.bookCab("Delhi", "Gudgoan");

        // Lambda Expression
        Cab cab2 = (source, destination) -> {
            System.out.println("Uber cab is booked from "+ source + " to " + destination);
            return "Price: 400";
        };
        cab2.bookCab("Delhi", "Noida");
    }
}
