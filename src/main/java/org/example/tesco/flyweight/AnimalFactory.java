package org.example.tesco.flyweight;

import java.util.HashMap;

public class AnimalFactory {

    HashMap<String, Animal> hashMap;

    public AnimalFactory() {
        hashMap = new HashMap<>();
    }

    public Animal getObject(String name) {
        if (hashMap.containsKey(name)) {
            return hashMap.get(name);
        } else if (name.equals("CAT")) {
            Animal cat = new Cat();
            hashMap.put(name, cat);
        } else if (name.equals("DOG")) {
            Animal cat = new Dog();
            hashMap.put(name, cat);
        }
        return hashMap.get(name);
    }
}
