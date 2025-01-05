package org.example.tesco.challenges;

public class FindDuplicateCharacterInString {
    public static void main(String[] args) {
        String name = "naveen";
        boolean[] chars = new boolean[256];
        char[] chars1 = name.toCharArray();
        for (char c : chars1) {
            if (chars[c]) {
                System.out.println(c);
            } else {
                chars[c] = true;
            }
        }
    }
}
