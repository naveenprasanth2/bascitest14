package org.example.chatgpt.dsa;

public class LongestPalindrome {
    static int resultStart;
    static int resultLength;

    public static void main(String[] args) {
        String s = "racecar";
        for (int start = 0; start < s.length(); start++) {
            expandCentre(s, start, start);
            expandCentre(s, start, start + 1);
        }

        System.out.println(s.substring(resultStart, (resultStart + resultLength)));
    }

    private static void expandCentre(String s, int begin, int end) {
        while (begin >= 0 && end < s.length() && (s.charAt(begin) == s.charAt(end))) {
            begin--;
            end++;
        }

        if (resultLength < ((end - 1) - (begin + 1) + 1)) {
            resultStart = begin + 1;
            resultLength = ((end - 1) - (begin + 1) + 1);
        }
    }
}
