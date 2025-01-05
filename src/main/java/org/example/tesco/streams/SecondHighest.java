package org.example.tesco.streams;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class SecondHighest {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1, "Alice", 60000),
                new Employee(2, "Bob", 45000),
                new Employee(3, "Charlie", 70000),
                new Employee(4, "David", 60000),
                new Employee(5, "Eve", 40000)
        );

        employees.stream()
                .sorted(Comparator.comparingInt(Employee::salary).reversed())
                .skip(1)
                .findFirst()
                .ifPresentOrElse(System.out::println, NoSuchElementException::new);
    }
}
