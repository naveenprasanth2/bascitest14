package org.example.lc;

import java.util.*;

public class SerializeDeserialize {

    public String serialize(String[] list) {
        // your code goes here
        StringBuilder builder = new StringBuilder();
        for(String s : list){
            int i = 0;
            while( i < s.length()){
                if(s.charAt(i) == '/' || s.charAt(i) == ';'){
                    builder.append('/');
                }
                builder.append(s.charAt(i));
                i++;
            }
            builder.append(';');
        }
        return builder.toString();
    }

    public String[] deserialize(String str){
        List<String> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == ';'){
                list.add(builder.toString());
                builder.setLength(0);
            }
            if(str.charAt(i) == '/'){
                i++;
                builder.append(str.charAt(i));
            }
            if(str.charAt(i) != '/' && str.charAt(i) != ';'){
                builder.append(str.charAt(i));
            }
        }
        return list.toArray(String[]::new);
    }

    public static void main(String[] args) {
        // debug your code below
        SerializeDeserialize serializer = new SerializeDeserialize();
        String[] list = {
                "Hello, World!",
                "NewLine",
                "Tab\tCharacter",
                "Special#Chars#Here",
                "   Leading/Spaces",
                "TrailingSpaces   ",
                "Unicode;",
                "",
                "This is a very long string with multiple spaces     and some # signs!!!"
        };
    
        // Serialize the list
        String serialized = serializer.serialize(list);
        System.out.println("Serialized output: " + serialized);
    
        // Deserialize the serialized string
        String[] deserialized = serializer.deserialize(serialized);

        System.out.println(Arrays.toString(deserialized));
    }
}