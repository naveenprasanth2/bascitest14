package org.example.tesco.threading;

public class InnerEval {
   static class Class {
        void test() {
            System.out.println("test");
        }
    }

    public static void main(String[] args) {
        Class classObj = new InnerEval.Class();
        classObj.test();
    }
}
