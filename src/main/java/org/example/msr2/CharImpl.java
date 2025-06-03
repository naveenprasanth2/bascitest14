package org.example.msr2;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CharImpl {
    public static void main(String[] args) {
        char[] chars = {'a', 'e', 'i', 'o', 'u'};
        Supplier<Stream<Character>> list = () ->  new String(chars).chars().mapToObj(x -> (char) x);
        list.get().forEach(System.out::println);
        list.get().map(Character::toUpperCase).forEach(System.out::println);
    }
}
