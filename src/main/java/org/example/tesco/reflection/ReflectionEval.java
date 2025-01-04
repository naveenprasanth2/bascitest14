package org.example.tesco.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionEval {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Cat cat = new Cat();
        Class<?> catClass = cat.getClass();
        Method[] methods = catClass.getDeclaredMethods();
        for (Method method: methods){
            if (method.isAnnotationPresent(Repeat.class)){
                Repeat repeat = method.getAnnotation(Repeat.class);
                int count = repeat.count();
                while (count > 0){
                    method.invoke(cat);
                    count--;
                }
            }
        }
    }
}
