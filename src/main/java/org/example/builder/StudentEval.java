package org.example.builder;

public class StudentEval {
    public static void main(String[] args) {
        Student student = Student.builder().setId(1).setName("Naveen").build();
        System.out.println(student);
    }
}
