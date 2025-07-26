package org.example.singleton;

import java.io.*;

public class SerialBreak {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Singleton singleton = Singleton.getSingleton();
        System.out.println(singleton);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("./ser.ser"));
        objectOutputStream.writeObject(singleton);
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("./ser.ser"));
        Singleton singleton1 = (Singleton) objectInputStream.readObject();
        System.out.println(singleton1);
    }
}
