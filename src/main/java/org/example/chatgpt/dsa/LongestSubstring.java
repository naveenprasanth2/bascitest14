package org.example.chatgpt.dsa;

import java.util.HashMap;

public class LongestSubstring {


    private int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int start = 0;
        HashMap<Character, Integer> visitedCharacters = new HashMap<>();

        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (visitedCharacters.containsKey(c) && visitedCharacters.get(c) >= left) {
                left = visitedCharacters.get(c) + 1;
            } else {
                visitedCharacters.put(c, right);
            }
            if (maxLength < (right - left + 1)) {
                start = left;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        System.out.println(s.substring(start, start + maxLength));
        return maxLength;
    }


    public static void main(String[] args) {
        LongestSubstring substring = new LongestSubstring();
        System.out.println(substring.lengthOfLongestSubstring("abffcefabcdeg"));
    }
}
