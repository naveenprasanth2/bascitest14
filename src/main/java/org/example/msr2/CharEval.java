package org.example.msr2;

import java.util.List;

public class CharEval {
    public static void main(String[] args) {
        char[] chars = {'a', 'e', 'i', 'o', 'u'};
        List<Character> list = new String(chars).chars().mapToObj(x -> (char) x).toList();
        list.forEach(System.out::println);
    }
}
