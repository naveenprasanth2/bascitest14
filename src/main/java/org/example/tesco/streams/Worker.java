package org.example.tesco.streams;

import java.util.List;

public record Worker(Integer id, String name, Integer salary, List<String> skills) {}
