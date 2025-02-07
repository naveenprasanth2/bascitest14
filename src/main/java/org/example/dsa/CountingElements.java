package org.example.dsa;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountingElements {
    public static void main(String[] args) {
        int[] a = {1, 1, 2, 2, 2, 3};
        Map<Integer, Long> test;
        long total;
        test = Arrays.stream(a).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        total = test.entrySet().stream().filter(x -> test.containsKey(x.getKey() + 1))
                        .mapToLong(Map.Entry::getValue)
                                .sum();

        System.out.println(total);
    }
}
