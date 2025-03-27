package org.example.cvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class Employee implements Comparable<Employee>{

    private int id;
    private String name;
    private int salary;

    @Override
    public int compareTo(Employee o){
        return this.id - o.id;
    }
}
