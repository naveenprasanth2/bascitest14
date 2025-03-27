package org.example.cvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EmployeeEval2 {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Employee employee = new Employee(1, "naveen", 90000);
        Method[] methods = employee.getClass().getDeclaredMethods();
        for (Method method : methods){
            if (method.isAnnotationPresent(Times.class)){
                Times times = method.getAnnotation(Times.class);
                int count = times.times();
                while (count > 0){
                    method.invoke(employee);
                    count--;
                }
            }
        }
    }
}
