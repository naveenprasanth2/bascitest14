package org.example.tesco.streams;

import java.util.List;

public class WorkerFlatMap {
    public static void main(String[] args) {
        List<Worker> workers = List.of(
                new Worker(1, "Alice", 60000, List.of("Java", "Spring")),
                new Worker(2, "Bob", 45000, List.of("Java", "Docker", "Kubernetes")),
                new Worker(3, "Charlie", 70000, List.of("Spring", "Kubernetes", "AWS")),
                new Worker(4, "David", 50000, List.of("Java", "Python")),
                new Worker(5, "Eve", 30000, List.of("Python", "AWS"))
        );

        List<String> skills = workers.stream().flatMap(x -> x.skills().stream())
                .distinct()
                .sorted(String::compareTo)
                .toList();

        System.out.println(skills);
    }
}
