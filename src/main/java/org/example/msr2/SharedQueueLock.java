package org.example.msr2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedQueueLock {
    private final int MAX_CAPACITY = 5;
    private Queue<Integer> queue;
    private final ReentrantLock lock;
    private final Condition spacesAvailable;
    private final Condition itemsAvailable;

    public SharedQueueLock() {
        queue = new LinkedList<>();
        lock = new ReentrantLock();
        spacesAvailable = lock.newCondition();
        itemsAvailable = lock.newCondition();
    }

    public void put(int val) {
        try {
            lock.lock();
            while (queue.size() == MAX_CAPACITY) {
                System.out.println("Queue is full");
                spacesAvailable.await();
            }
            queue.offer(val);
            System.out.println(STR."The value \{val} is inserted into the queue");
            itemsAvailable.signalAll();
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
                System.out.println("Queue is empty");
                itemsAvailable.await();
            }
            System.out.println(STR."The value fetched from the queue \{queue.poll()}");
            spacesAvailable.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
           lock .unlock();
        }
    }
}
