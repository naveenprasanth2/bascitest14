package org.example.tesco.streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FirstTwoPerDepartment {
    public static void main(String[] args) {
        List<StaffMember> staffMembers = List.of(
                new StaffMember(1, "Alice", 60000.0, "Engineering"),
                new StaffMember(2, "Bob", 45000.0, "Engineering"),
                new StaffMember(3, "Charlie", 70000.0, "HR"),
                new StaffMember(4, "David", 50000.0, "Engineering"),
                new StaffMember(5, "Eve", 30000.0, "HR"),
                new StaffMember(6, "Frank", 80000.0, "Marketing"),
                new StaffMember(7, "Grace", 55000.0, "Marketing"),
                new StaffMember(8, "Hank", 90000.0, "Marketing")
        );
     Map<String, List<StaffMember>> test = staffMembers.stream().collect(Collectors.groupingBy(StaffMember::department,
               Collectors.collectingAndThen(Collectors.toList(),
                       staffMembers1 -> staffMembers1.stream()
                               .sorted(Comparator.comparingDouble(StaffMember::salary)
                                       .reversed()).limit(2).toList())));

        System.out.println(test);
    }
}
