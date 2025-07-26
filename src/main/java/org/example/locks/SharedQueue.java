package org.example.locks;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedQueue {
    private Queue<Integer> queue;
    private int capacity;
    ReentrantLock lock;
    Condition spacesAvailable;
    Condition itemAvailable;

    public SharedQueue(int capacity) {
        queue = new LinkedList<>();
        this.capacity = capacity;
        lock = new ReentrantLock();
        spacesAvailable = lock.newCondition();
        itemAvailable = lock.newCondition();
    }

    public void put(int val) throws InterruptedException {
        lock.lock();
        try {
            while (this.capacity == queue.size()) {
                System.out.println(STR."Waiting for space");
                spacesAvailable.await();
            }
            queue.offer(val);
            System.out.println("The value added in queue");
            itemAvailable.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                System.out.println("Queue has no value");
                itemAvailable.await();
            }
            System.out.println(STR."The value taken is \{queue.poll()}");
            spacesAvailable.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
