package org.example.dsa.annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationEval {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Animal animal = new Animal();
        Method[] methods = animal.getClass().getDeclaredMethods();
        for (Method method : methods){
            if (method.isAnnotationPresent(Repeat.class)){
                Repeat repeat = method.getAnnotation(Repeat.class);
                int times = repeat.times();
                while (times > 0){
                    method.invoke(animal);
                    times--;
                }
            }
        }
    }
}
