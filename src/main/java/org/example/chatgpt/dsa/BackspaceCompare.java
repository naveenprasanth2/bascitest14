package org.example.chatgpt.dsa;

import java.util.Stack;

public class BackspaceCompare {
    static Stack<Character> st1 = new Stack<>();
    static Stack<Character> st2 = new Stack<>();

    public static void main(String[] args) {
        String s1 = "ab#c";
        String s2 = "acc#";
        fillStack(s1, st1);
        fillStack(s2, st2);
        System.out.println(st1.equals(st2));
    }

    private static void fillStack(String s, Stack<Character> st) {
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '#' && !st.isEmpty()) {
                st.pop();
            } else {
                st.push(c);
            }
        }
    }
}
