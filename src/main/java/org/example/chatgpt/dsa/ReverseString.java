package org.example.chatgpt.dsa;

public class ReverseString {
    public static void main(String[] args) {
        String name = "naveen";
        char[] reversed = new char[name.length()];
        for (int i = name.length() - 1; i >= 0; i--) {
            reversed[name.length() - 1 - i] = name.charAt(i);
        }
        System.out.println(String.valueOf(reversed));
    }
}
