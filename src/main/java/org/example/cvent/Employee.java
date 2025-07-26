package org.example.cvent;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Employee implements Comparable<Employee>{

    private int id;
    private String name;
    private int salary;

    public Employee(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return STR."Employee{id=\{id}, name='\{name}\{'\''}, salary=\{salary}\{'}'}";
    }

    @Override
    public int compareTo(Employee o){
        return this.id - o.id;
    }
    @Times(times = 10)
    public void print(){
        System.out.println(this);
    }
}
