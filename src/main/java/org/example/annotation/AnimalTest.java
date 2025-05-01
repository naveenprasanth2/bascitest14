package org.example.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnimalTest {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Animal animal = new Animal();
        Class<?> animalClass = animal.getClass();
        Method[] methods = animalClass.getDeclaredMethods();
        for(Method method : methods){
            if(method.isAnnotationPresent(MethodAnnotation.class)){
                MethodAnnotation methodAnnotation = method.getAnnotation(MethodAnnotation.class);
                int times = methodAnnotation.times();
                while(times > 0){
                    method.invoke(animal);
                    times--;
                }
            }
        }
    }
}
