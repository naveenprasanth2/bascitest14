package org.example.ms;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Person {
    private String id;
    private String name;
    private String department;

    public List<Person> sort(List<Person> list, String[] criteria) {

        Collections.sort(list, (a, b) -> {
            for (String fieldName : criteria) {
                try {
                    Field field = Person.class.getDeclaredField(fieldName);
                    field.setAccessible(true);

                    Object varA = field.get(a);
                    Object varB = field.get(b);

                    if (varA == null && varB == null) continue;
                    if (varA == null) return -1;
                    if (varB == null) return 1;
                    int result = ((Comparable) varA).compareTo(varB);
                    if (result != 0) return result;
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            return 0;
        });


        return list;
    }
}
