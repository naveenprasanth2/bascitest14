package org.example.msr2;

import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {
    private final int MAX_CAPACITY = 5;
    private Queue<Integer> queue = new LinkedList<>();

    public synchronized void put(int val) throws InterruptedException {
        while (queue.size() == MAX_CAPACITY) {
            System.out.println("Inside wait");
            wait();
        }
        queue.add(val);
        System.out.println(STR."\{val} value has been added");
        notify();
    }

    public synchronized void take() throws InterruptedException {
        while (queue.isEmpty()){
            System.out.println("The queue is empty");
            wait();
        }
        System.out.println(STR."The value fetched from the queue is \{queue.poll()}");
        notify();
    }
}
