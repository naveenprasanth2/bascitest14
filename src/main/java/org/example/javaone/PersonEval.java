package org.example.javaone;

import java.util.HashMap;
import java.util.Map;

public class PersonEval {
    public static void main(String[] args) {
        Person person = new Person(1, "naveen");
        Person person1 = new Person(2, "prasanth");
        Map<Integer, Person> map = new HashMap<>();
        map.put(1, person);
        map.put(2, person1);
        System.out.println(map);
        Person person2 = map.get(1);
        person2.setId(3);
        System.out.println(map);
    }
}
