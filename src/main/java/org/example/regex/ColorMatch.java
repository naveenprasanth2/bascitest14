package org.example.regex;

import java.util.List;
import java.util.Arrays;
import java.util.regex.Pattern;

public class ColorMatch {
    public static void main(String[] args) {
        List<String> testStrings = Arrays.asList(
                "Here are some colors: #FF5733, #BADA55, #000000.",
                "Invalid colors: #FFF, #123ABCG, #1234567"
        );

        List<String> test = testStrings.stream()
                .flatMap(x -> Arrays.stream(x.split(" ")))
                .map(x -> x.replaceAll("[,;.]$", ""))
                .filter(x -> Pattern.matches("#[\\dA-Fa-f]{6}", x))
                .toList();

        System.out.println(test);

    }
}
