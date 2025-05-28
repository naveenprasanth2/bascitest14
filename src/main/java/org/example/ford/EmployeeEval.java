package org.example.ford;

import java.util.List;
public class EmployeeEval {
    public static void main(String[] args) {
        Employee e1 = new Employee("person1", 40000);
        Employee e2 = new Employee("person2", 80000);
        String[] sortCondition = {"salary", "name"};
        List<Employee> employees = List.of(e1, e2);
        List<Employee> result = employees.stream()
                .sorted((x1, x2) -> {
                    int sortResult = 0;
                    for (String value : sortCondition) {
                        if (value.equals("name")) {
                            sortResult = x1.name().compareTo(x2.name());
                        }
                        if (value.equals("salary")) {
                            sortResult = (int) (x2.salary() - x1.salary());
                        }
                    }
                    return sortResult;
                })
                .toList();
        System.out.println(result);
    }
}
