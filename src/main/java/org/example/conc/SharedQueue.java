package org.example.conc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedQueue {
    private Queue<Integer> queue;
    private final int MAX_CAPACITY = 5;
    private ReentrantLock lock;
    private Condition spacesAvailable;
    private Condition itemAvailable;

    public SharedQueue() {
        queue = new LinkedList<>();
        lock = new ReentrantLock();
        spacesAvailable = lock.newCondition();
        itemAvailable = lock.newCondition();
    }

    public void put(int val) {
        try {
            lock.lock();
            while (queue.size() == MAX_CAPACITY) {
                System.out.println("The queue is full");
                spacesAvailable.await();
            }
            queue.offer(val);
            System.out.println("The value is inserted in the queue");
            itemAvailable.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void take() {
        try {
            lock.lock();
            while (queue.isEmpty()) {
                System.out.println("No item available in the queue");
                itemAvailable.await();
            }
            System.out.println(STR."The item taken from queue is \{queue.poll()}");
            spacesAvailable.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
