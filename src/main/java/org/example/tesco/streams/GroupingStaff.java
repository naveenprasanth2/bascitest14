package org.example.tesco.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingStaff {
    public static void main(String[] args) {
        List<StaffMember> staffMembers = List.of(
                new StaffMember(1, "Alice", 60000.0, "Engineering"),
                new StaffMember(2, "Bob", 45000.0, "Engineering"),
                new StaffMember(3, "Charlie", 70000.0, "HR"),
                new StaffMember(4, "David", 50000.0, "Engineering"),
                new StaffMember(5, "Eve", 30000.0, "HR"),
                new StaffMember(6, "Frank", 80000.0, "Marketing"),
                new StaffMember(7, "Grace", 55000.0, "Marketing")
        );

        Map<String, Map<String, List<StaffMember>>> test = staffMembers.stream()
                .collect(Collectors.groupingBy(StaffMember::department, Collectors.groupingBy(staffMember -> {
                    if (staffMember.salary() <= 50000) {
                        return "Low";
                    } else if (staffMember.salary() < 75000) {
                        return "Medium";
                    }
                    return "High";

                })));

        System.out.println(test);
    }

}
