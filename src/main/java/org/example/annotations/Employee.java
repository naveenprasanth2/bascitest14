package org.example.annotations;

public class Employee {
    @TimesAnnotation(times = 10)
    void test() {
        System.out.println("Employee");
    }
}
