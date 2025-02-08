package org.example.chatgpt.dsa;

import java.util.Stack;

public class ValidParenthesis1 {
    public static void main(String[] args) {
        String s = "(){()}";
        String s1 = "{}";
        System.out.println(checkValid(s));
        System.out.println(checkValid(s1));
    }

    private static boolean checkValid(String s) {
        char[] array = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : array) {
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else if (!stack.isEmpty()) {
                char popped = stack.pop();
                if ((c == '}' && popped != '{') || (c == ')' && popped != '(') || (c == ']' && popped != '[')) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
