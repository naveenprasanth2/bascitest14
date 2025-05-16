package org.example.ms;

import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Person2 {
    private int age;
    private String name;

    @Override
    public String toString() {
        return STR."Person2{age=\{age}, name='\{name}\{'\''}\{'}'}";
    }

    public static List<Person2> sort(List<Person2> list, String[] criteria) {
        Collections.sort(list, (a, b) -> {
            for (String condition : criteria) {
                try {
                    Field field = a.getClass().getDeclaredField(condition);
                    field.setAccessible(true);
                    Object varA = field.get(a);
                    Object varB = field.get(b);
                    if (varA == null && varB == null) continue;
                    if (varA == null) return -1;
                    if (varB == null) return 1;
                    int result = ((Comparable) varA).compareTo(varB);
                    if (result != 0) return result;
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            return 0;
        });

        return list;
    }

    public static void main(String[] args) {
        Person2 person = new Person2();
        person.age = 5;
        person.name = "zoolo";

        Person2 person1 = new Person2();
        person1.age = 10;
        person1.name = "prasanth";
        List<Person2> list = new ArrayList<>();
        list.add(person1);
        list.add(person);
        System.out.println(Person2.sort(list, new String[]{"age", "name"}));
    }
}
