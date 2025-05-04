package org.example.multi.inner;

public class Outer {

    public static void main(String[] args) {
        Inner inner = new Outer.Inner();
        inner.test();
    }

    private static class Inner {
        public void test() {
            System.out.println("Inside inner class");
        }
    }
}
