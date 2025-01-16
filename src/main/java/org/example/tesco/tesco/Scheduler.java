package org.example.tesco.tesco;

import java.util.List;

public class Scheduler {
    public static void main(String[] args) {
        Department bakery = new Department("Bakery", 8, 10);
        Department checkout = new Department("Checkout", 10, 12);
        Department diary = new Department("Diary", 14, 19);
        Employee employee = new Employee("naveen", 29, List.of(bakery, checkout, diary));
        FindShifts findShifts = new FindShifts();
        System.out.println((findShifts.findShifts(employee, 8, 19)));
    }
}
