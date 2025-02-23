package org.example.chatgpt.dsa;

import java.util.ArrayList;

public class LongestPalindrome1 {
    static int resultIndex;
    static int resultLength;

    public static void main(String[] args) {
        String s = "racecar";
        for (int start = 0; start < s.length(); start++) {
            expandRange(s, start, start);
            expandRange(s, start, start + 1);
        }
        System.out.println(s.substring(resultIndex, resultIndex + resultLength));
    }

    private static void expandRange(String s, int begin, int end) {
        if (s.length() <= 1) {
            resultIndex = 0;
            resultLength = s.length();
        }

        while (begin >= 0 && end < s.length() && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }

        if (resultLength < (end - 1) - (begin + 1) + 1) {
            resultIndex = begin + 1;
            resultLength = (end - 1) - (begin + 1) + 1;
        }

    }
}
