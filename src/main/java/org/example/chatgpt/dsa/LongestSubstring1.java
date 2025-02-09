package org.example.chatgpt.dsa;

import java.util.HashMap;

public class LongestSubstring1 {

    public static void main(String[] args) {
        LongestSubstring1 substring = new LongestSubstring1();
        System.out.println(substring.lengthOfLongestSubstring("abffcefabcdeg"));
    }

    private int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) return s.length();
        HashMap<Character, Integer> visitedCharacters = new HashMap<>();
        int maxLength = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (visitedCharacters.containsKey(c) && visitedCharacters.get(c) >= left) {
                left = visitedCharacters.get(c) + 1;
            } else {
                visitedCharacters.put(c, right);
            }
            if (maxLength < (right - left + 1)) {
                maxLength = (right - left + 1);
            }
        }
        return maxLength;
    }
}
