package org.example.cvent;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionEval {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> employeeClass = Employee.class;
        Constructor<?> employeeConstructor = employeeClass.getDeclaredConstructor();
        employeeConstructor.setAccessible(true);
        Employee employee = (Employee) employeeConstructor.newInstance();
        employee.setId(1);;
        employee.setName("naveen");
        employee.setSalary(87000);
        System.out.println(employee);
    }
}
