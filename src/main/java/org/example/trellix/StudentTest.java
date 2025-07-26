package org.example.trellix;

import java.util.HashMap;
import java.util.Map;

public class StudentTest {
    public static void main(String[] args) {
        String name = "Naveen";
        Student student = new Student(name);
        String newName = student.test();
        System.out.println(name.equals(newName));
    }
}
