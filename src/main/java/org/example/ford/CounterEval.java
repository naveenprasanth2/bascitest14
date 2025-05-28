package org.example.ford;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CounterEval {
    public static void main(String[] args) {
        List<List<String>> list = List.of(List.of("naveen", "prasanth"), List.of("test", "prasanth"));
        List<Map.Entry<String, Long>> result = list.stream().flatMap(x -> x.stream())
                .map(x -> Arrays.stream(x.split("")).toList())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .toList();
        System.out.println(result);
    }
}
