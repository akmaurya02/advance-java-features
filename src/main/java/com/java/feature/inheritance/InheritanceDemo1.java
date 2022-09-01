package com.java.feature.inheritance;

import java.io.IOException;

class Parent {
    void test () throws Exception{
        System.out.println("Parent");
    }
}

class Child extends Parent {
    void test () throws IOException {
        System.out.println("Child");
    }

    void display() throws Exception {
        super.test();
        System.out.println("Child display");
    }
}

public class InheritanceDemo1 {
    public static void main(String[] args) throws Exception {

        Parent p = new Child();
        p.test();

        Child c = new Child();
        c.test();
        c.display();
    }
}
