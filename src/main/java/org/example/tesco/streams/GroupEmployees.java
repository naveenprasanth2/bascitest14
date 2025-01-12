package org.example.tesco.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupEmployees {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1, "Alice", 60000),
                new Employee(2, "Bob", 45000),
                new Employee(3, "Charlie", 70000),
                new Employee(4, "David", 80000),
                new Employee(5, "Eve", 30000)
        );

        Map<String, List<Employee>> test = employees.stream()
                .collect(Collectors.groupingBy(employee -> {
                    if (employee.salary() <= 50000) {
                        return "Low";
                    } else if (employee.salary() <= 75000) {
                        return "Mid";
                    } else {
                        return "High";
                    }
                }));

        System.out.println(test);
    }
}
