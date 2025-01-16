package org.example.tesco.tesco;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private String name;
    private int age;
    private List<Department> departments;
}
