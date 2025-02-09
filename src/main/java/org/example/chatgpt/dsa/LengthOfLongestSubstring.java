package org.example.chatgpt.dsa;

import java.util.HashMap;

public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int start = 0;
        int maxLength = 0;
        if (s.length() <= 1) return s.length();
        HashMap<Character, Integer> visitedCharacters = new HashMap<>();
        for (int right = 0, left = 0; right < s.length(); right++) {
            char current = s.charAt(right);
            if (visitedCharacters.containsKey(current) && visitedCharacters.get(current) >= left){
                left = visitedCharacters.get(current) + 1;
            }
            if (maxLength < (right - left + 1)){
                start = left;
            }
            maxLength = Math.max(maxLength, right - left + 1);
            visitedCharacters.put(current, right);
        }
        System.out.println(start);
        System.out.println(maxLength);
        System.out.println(s.substring(start, start + maxLength));
        return maxLength;
    }


    public static void main(String[] args) {
        LengthOfLongestSubstring substring = new LengthOfLongestSubstring();
        System.out.println(substring.lengthOfLongestSubstring("abffcefabcdeg"));
    }
}
