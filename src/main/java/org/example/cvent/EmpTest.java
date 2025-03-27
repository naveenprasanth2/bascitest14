package org.example.cvent;

import java.util.Arrays;

import static org.example.cvent.EmplInterface.comparator;

public class EmpTest {
    public static void main(String[] args) {
        Employee employee1 = new Employee(1, "naveen", 90000);
        Employee employee2 = new Employee(2, "prasanth", 1000000);
        Employee[] employees = new Employee[]{employee2, employee1};
        System.out.println(Arrays.toString(employees));
        Arrays.sort(employees);
        System.out.println(Arrays.toString(employees));
        Arrays.sort(employees, comparator);
        System.out.println(Arrays.toString(employees));
    }
}
