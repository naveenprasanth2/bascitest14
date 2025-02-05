package org.example.dsa;

import java.util.HashSet;

public class LongestSubString {
    public static void main(String[] args) {
        String s = "abcdabcmnopqrstuvwxyzbbabcdef";
        int left = 0;
        int right = 1;
        int length = 0;
        HashSet<Character> hashSet = new HashSet<>();
        hashSet.add(s.charAt(left));
        while (right < s.length()) {
            if (!hashSet.add(s.charAt(right))) {
                left++;
            }
            right++;
            if (length < (right - left)) {
                length = right - left;
            }
        }
        System.out.println(length);
    }
}
