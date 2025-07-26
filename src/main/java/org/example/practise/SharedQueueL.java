package org.example.practise;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedQueueL {
    Queue<Integer> queue = new LinkedList<>();
    private final int MAX_CAPACITY = 5;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition spaceAvailable = lock.newCondition();
    private final Condition itemAvailable = lock.newCondition();

    public void put(int val) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() >= MAX_CAPACITY) {
                System.out.println("The queue is full");
                spaceAvailable.await();
            }
            queue.offer(val);
            itemAvailable.signal();
        } finally {
            lock.unlock();
        }
    }

    public void take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                itemAvailable.await();
            }
            System.out.println(queue.poll());
            spaceAvailable.signal();
        } finally {
            lock.unlock();
        }

    }
}
