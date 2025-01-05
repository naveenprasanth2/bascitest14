package org.example.tesco.challenges;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindRepeatAndSort {
    public static void main(String[] args) {
        List<String> test = List.of("cat", "mat", "bat", "bat", "cat");
        List<Map.Entry<String, Long>> values = test.stream()
                .collect(Collectors.groupingBy(Function.identity(),
//                        LinkedHashMap::new,
                        Collectors.counting()))
                .entrySet()
                .stream()
//                .sorted((x1, x2) -> {
//                    if (!Objects.equals(x1.getValue(), x2.getValue())) {
//                        return ((int) (x2.getValue() - x1.getValue()));
//                    }
//                    return x1.getKey().compareTo(x2.getKey());
//                })
                .toList();

        System.out.println(values);
    }
}
