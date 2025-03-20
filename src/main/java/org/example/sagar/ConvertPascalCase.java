package org.example.sagar;

public class ConvertPascalCase {
    public static void main(String[] args) {
        String name = "public void summaThaIruken()";
        char[] chars = name.toCharArray();
        StringBuilder builder = new StringBuilder();
        for(char c : chars){
            if(c >= 65 && c <= 90 ){
                c+=32;
                builder.append("_");
            }
            builder.append(c);
        }
        System.out.println(builder);
    }
}
