package org.example.tesco.flyweight;

public class Dog implements Animal {
    private String name;

    @Override
    public void setName(String name) {
        this.name = STR."\{name} - DOG";
    }

    @Override
    public void printAttributes() {
        System.out.println(name);
        System.out.println(CommonProps.legs);
        System.out.println(CommonProps.head);
        System.out.println(CommonProps.nose);
        System.out.println(CommonProps.tail);
    }
}
