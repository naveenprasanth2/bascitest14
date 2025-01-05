package org.example.tesco.challenges;

import java.util.HashSet;
import java.util.Set;

public class FindUncommon {
    static String str1 = "characters";
    static String str2 = "alphabets";

    public static void main(String[] args) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char c : chars1){
            if (checkIfUncommon(str2, c)){
                set.add(c);
            }
        }
        for (char c : chars2){
            if (checkIfUncommon(str1, c)){
                set.add(c);
            }
        }

        System.out.println(set);
    }


    private static boolean checkIfUncommon(String str, char c) {
        return str.indexOf(c) == -1;
    }
}
