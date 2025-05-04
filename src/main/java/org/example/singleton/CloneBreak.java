package org.example.singleton;

public class CloneBreak {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getSingleton();
        System.out.println(singleton);

        Singleton  singleton1 = singleton.clone();
        System.out.println(singleton1);
    }
}
