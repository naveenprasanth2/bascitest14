package org.example.practise;

public class CounterExample {
    public int count;

    public void increaseCount() {
//        synchronized (CounterExample.class) {
            count++;
//        }
    }
}