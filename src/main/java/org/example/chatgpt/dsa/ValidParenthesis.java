package org.example.chatgpt.dsa;

import java.util.List;
import java.util.Stack;

public class ValidParenthesis {
    public static void main(String[] args) {
        String s = "(){()}";
        System.out.println(checkValid(s));
    }

    private static boolean checkValid(String str) {
        if (str.length() == 1) return false;
        if (str.isEmpty()) return true;
        Stack<Character> characterStack = new Stack<>();
        List<Character> opening = List.of('{', '(', '<', '[');
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (opening.contains(c)) {
                characterStack.add(c);
            } else if (!characterStack.isEmpty()) {
                char popped = characterStack.pop();
                if ((popped == '{' && c != '}') || (popped == '(' && c != ')') || (popped == '[' && c != ']')) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return characterStack.isEmpty();
    }
}
