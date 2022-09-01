package com.java.feature.inheritance;

interface I {
    void test ();
}

class A implements I {
    @Override
    public void test() {
        System.out.println("A");
    }
}

public class InheritanceDemo2 {
    public static void main(String[] args) {
        I i = new A();
        i.test();
        System.out.println(i.toString());
    }
}
