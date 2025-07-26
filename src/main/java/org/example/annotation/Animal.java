package org.example.annotation;


public class Animal {
    @MethodAnnotation(times = 777)
    public void times() {
        System.out.println("animal");
    }
}
