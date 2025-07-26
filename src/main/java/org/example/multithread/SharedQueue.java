package org.example.multithread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedQueue {
    private final int capacity = 5;
    private final Queue<Integer> queue;
    private final ReentrantLock lock;
    private final Condition spaceAvailable;
    private final Condition itemAvailable;

    public SharedQueue() {
        this.queue = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.spaceAvailable = lock.newCondition();
        this.itemAvailable = lock.newCondition();
    }

    public void put(int val) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                System.out.println("Queue is full");
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
                System.out.println("Queue is empty");
                itemAvailable.await();
            }
            System.out.println(STR."The value from consumer is \{queue.poll()}");
            spaceAvailable.signal();
        } finally {
            lock.unlock();
        }
    }

}

class Producer implements Runnable{
    SharedQueue queue;
    public Producer(SharedQueue queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        int value = 0;
        while (true){
            try {
                Thread.sleep(200);
                queue.put(value++);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Consumer implements Runnable{
    SharedQueue queue;
    public Consumer(SharedQueue queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(500);
                queue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}