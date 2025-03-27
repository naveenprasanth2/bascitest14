package org.example.cvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class Employee implements Comparable<Employee>{

    private int id;
    private String name;
    private int salary;

    @Override
    public int compareTo(Employee o){
        return this.id - o.id;
    }
    @Times(times = 10)
    public void print(){
        System.out.println(this);
    }
}
