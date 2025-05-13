package org.example.ms;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Person1 {
    private String id;
    private String name;
    private String department;

    public List<Person1> sort(List<Person1> list, String[] criteria){

        list.sort((a, b) -> {
            for (String criterion : criteria) {
                String[] parts = criterion.split(":");
                String fieldName = parts[0];
                boolean ascending = parts[1].equalsIgnoreCase("asc");
                try {
                    Field field = Person1.class.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    Object varA = field.get(a);
                    Object varB = field.get(b);

                    if (varA == null && varB == null) continue;
                    if (varA == null) return -1;
                    if (varB == null) return 1;

                    // Check if the field is Comparable
                    if (varA instanceof Comparable && varB instanceof Comparable) {
                        @SuppressWarnings("unchecked")
                        int result = ((Comparable<Object>) varA).compareTo(varB);
                        if (result != 0) return ascending ? result : -result;
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            return 0;
        });




        return list;
    }
}
