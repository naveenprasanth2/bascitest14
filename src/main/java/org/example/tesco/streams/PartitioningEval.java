package org.example.tesco.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitioningEval {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1, "Alice", 60000),
                new Employee(2, "Bob", 45000),
                new Employee(3, "Charlie", 70000),
                new Employee(4, "David", 50000),
                new Employee(5, "Eve", 30000)
        );

        Map<Boolean, List<Employee>> test = employees.stream().collect(Collectors.partitioningBy(employee -> employee.salary() > 50000));
        System.out.println(test);
    }
}
