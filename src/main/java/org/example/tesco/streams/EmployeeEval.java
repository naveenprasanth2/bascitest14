package org.example.tesco.streams;

import java.util.Comparator;
import java.util.List;

public class EmployeeEval {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1, "Alice", 60000),
                new Employee(2, "Bob", 45000),
                new Employee(3, "Charlie", 70000),
                new Employee(4, "David", 40000)
        );

       List<String> emps =  employees.stream().filter(x -> x.salary()> 50000)
                .map(Employee::name)
                .sorted(String::compareTo)
                .toList();
        System.out.println(emps);
    }
}
