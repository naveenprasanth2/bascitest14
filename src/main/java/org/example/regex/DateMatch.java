package org.example.regex;

import java.util.List;
import java.util.Arrays;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateMatch {
    public static void main(String[] args) {
        List<String> testStrings = Arrays.asList(
                "Today's date is 12/25/2021.",
                "My birthday is on 05/15/1995.",
                "Invalid dates: 25/12/2021, 2021/12/25"
        );

        Pattern pattern = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");

        List<String> test = testStrings.stream().flatMap(text -> {
            Matcher matcher = pattern.matcher(text);
            return matcher.results().map(MatchResult::group);
        }).toList();

        System.out.println(test);
    }
}
