package org.example.cvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeEval {
//    Given A company's organizational structure is represented as
//            1: 2, 3, 4
//    In the above employees with id 2, 3 and 4 report to 1
//
//    Assume the following hierarchy.
//            1: 2, 3, 4
//            3: 5, 6, 7
//            5: 8, 9, 10
//
//    Given an employee Id, return all the employees reporting to him directly or indirectly.
    static Map<Integer, List<Integer>> map = new HashMap<>();

    public static void main(String[] args) {
        map.put(1, List.of(2, 3, 4));
        map.put(3, List.of(5, 6, 7));
        map.put(5, List.of(8, 9, 10));

        System.out.println(getAllEmployees(8).stream().distinct().toList());
        System.out.println(getAllEmployees(10).stream().distinct().toList());

    }

    private static List<Integer> getAllEmployees(int id) {
        List<Integer> employees = new ArrayList<>();
        int i = 0;
        while (map.containsKey(id) || i < employees.size()) {
            if (map.containsKey(id)) {
                employees.addAll(map.get(id));
            }
            id = i++;
        }
        return employees;
    }


}
