package org.example.cvent;

import java.util.function.Function;

public class FunctionalEval {
    public static void main(String[] args) {
        Function<String, Integer> function = String::length;
        System.out.println(function.apply("naveen"));
    }
}
