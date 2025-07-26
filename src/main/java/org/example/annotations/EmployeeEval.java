package org.example.annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EmployeeEval {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Employee employee = new Employee();
        Class<?> employeeClass = employee.getClass();
        Method[] methods = employeeClass.getDeclaredMethods();
        for (Method method : methods){

            TimesAnnotation annotation = method.getDeclaredAnnotation(TimesAnnotation.class);
            int times = annotation.times();
            while (times > 0){
                method.invoke(employee);
                times--;
            }
        }
    }
}
