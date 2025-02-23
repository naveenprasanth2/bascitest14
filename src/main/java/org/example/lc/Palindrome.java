package org.example.lc;

public class Palindrome {

    static boolean isPalindrome(String s) {
        s = s.toLowerCase().replaceAll("[^a-zA-Z]", "");
        int left = 0;
        int right = s.length() - 1;
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        // debug your code below
        System.out.println(isPalindrome("race a car"));
    }
}