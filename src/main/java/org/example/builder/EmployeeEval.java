package org.example.builder;

public class EmployeeEval {
    public static void main(String[] args) {
        Employee employee = Employee.builder().setId(1).setName("naveen").build();
        employee.print();
    }
}
