package org.example.lc;

import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecode {
    static String encodedString;
    static List<String> decodedString;

    public void encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            int i = 0;
            while (i < str.length()) {
                if (str.charAt(i) == ';' || str.charAt(i) == '/') {
                    sb.append("/");
                }
                sb.append(str.charAt(i));
                i++;
            }
            sb.append(";");
        }
        encodedString = sb.toString();
    }

    public void decode(String encodedString) {
        decodedString = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < encodedString.length()) {
            if (encodedString.charAt(i) == '/') {
                sb.append(encodedString.charAt(i + 1));
                i+=2;
            } else if (encodedString.charAt(i) != ';') {
                sb.append(encodedString.charAt(i));
                i++;
            }else if(encodedString.charAt(i) == ';'){
                decodedString.add(sb.toString());
                sb.setLength(0);
                i++;
            }
        }
        System.out.println(decodedString);
    }

    public static void main(String[] args) {
        List<String> codingString = List.of("Hello, World!",
                "NewLine",
                "Tab\tCharacter",
                "Special#Chars#Here",
                "   Leading/Spaces",
                "TrailingSpaces   ",
                "Unicode;",
                "",
                "This is a very long string with multiple spaces     and some # signs!!!"
        );
        System.out.println(codingString);
        EncodeAndDecode encodeAndDecode = new EncodeAndDecode();
        encodeAndDecode.encode(codingString);
        encodeAndDecode.decode(encodedString);
    }
}
