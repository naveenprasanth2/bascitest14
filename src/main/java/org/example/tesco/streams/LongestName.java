package org.example.tesco.streams;

import java.util.Comparator;
import java.util.List;

public class LongestName {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1, "Alice", 60000),
                new Employee(2, "Bob", 45000),
                new Employee(3, "Charlotte", 70000),
                new Employee(4, "David", 80000),
                new Employee(5, "Evelyn", 30000)
        );

        Employee test = employees.stream().max(Comparator.comparing(employee -> employee.name().length())).orElse(null);
        System.out.println(test);
    }
}
