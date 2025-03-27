package org.example.cvent;

import java.util.Comparator;

public class EmplInterface {

    static Comparator<Employee> comparator = (x1, x2) -> x2.getSalary() - x1.getSalary();
}
