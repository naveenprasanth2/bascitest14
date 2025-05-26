package org.example.reflection;


import java.io.*;
import java.lang.reflect.Constructor;

public class ReflectionBreak {
    public static void main(String[] args) throws NoSuchMethodException, IOException, ClassNotFoundException {
        Singleton singleton = Singleton.getSingleton();
        Class<?> singletonClass = singleton.getClass();
        Constructor<?> constructor = singletonClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("./ser.ser"));
        objectOutputStream.writeObject(singleton);
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./ser.ser"));
        Singleton singleton1 = (Singleton) inputStream.readObject();
        System.out.println(singleton1);
        System.out.println(singleton);
    }
}
