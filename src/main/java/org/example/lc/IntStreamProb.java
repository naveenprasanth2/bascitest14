package org.example.lc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class IntStreamProb {
    static int[] nums = {1, 2, 4, 4};

    public static void main(String[] args) {
        int i = Arrays.stream(nums).boxed().mapToInt(Integer::intValue).max().orElse(0);
        String[] strings = {"na", "ve", "en"};
        String name;
        name = Stream.of(strings).min(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println(name);
    }
}
