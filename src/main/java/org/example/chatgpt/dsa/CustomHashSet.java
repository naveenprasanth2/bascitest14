package org.example.chatgpt.dsa;

import java.util.HashMap;

public class CustomHashSet {

    HashMap<Integer, Object> setMap;
    private static final Object DUMMY_VALUE = new Object();

    public CustomHashSet() {
        setMap = new HashMap<>();
    }

    public void add(int number) {
        setMap.put(number, DUMMY_VALUE);
    }

    public void remove(int number) {
        setMap.remove(number);
    }

    public boolean contains(int number) {
        return setMap.containsKey(number);
    }

    public void print() {
        setMap.keySet().forEach(System.out::println);
    }
}

class HashMapEval {
    public static void main(String[] args) {
        CustomHashSet customHashSet = new CustomHashSet();
        customHashSet.add(1);
        customHashSet.add(2);
        customHashSet.add(1);
        System.out.println(customHashSet.contains(1));
        System.out.println(customHashSet.contains(4));
        customHashSet.print();
    }
}