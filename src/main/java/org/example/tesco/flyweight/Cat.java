package org.example.tesco.flyweight;

import static java.lang.StringTemplate.STR;

public class Cat implements Animal {
    private String name;

    @Override
    public void setName(String name) {
        this.name = STR."\{name} - CAT";
    }

    @Override
    public void printAttributes() {
        System.out.println(name);
        System.out.println(STR."The no of legs is \{CommonProps.legs}");
        System.out.println(STR."The no of head is \{CommonProps.head}");
        System.out.println(STR."The no of nose is \{CommonProps.nose}");
        System.out.println(STR."The no of tail is \{CommonProps.tail}");
    }
}
