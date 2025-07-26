package org.example.tesco.threading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedQueue {
    private final Queue<Integer> queue;
    private final ReentrantLock lock;
    private final Condition spaceAvailable;
    private final Condition itemsAvailable;
    private final int CAPACITY;

    public SharedQueue(int capacity) {
        queue = new LinkedList<>();
        lock = new ReentrantLock();
        spaceAvailable = lock.newCondition();
        itemsAvailable = lock.newCondition();
        CAPACITY = capacity;
    }

    public void put(int val) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == CAPACITY) {
                System.out.println(STR."The val needs to be inserted \{val}");
                spaceAvailable.await();
            }
            queue.offer(val);
            itemsAvailable.signal();
        } finally {
            lock.unlock();
        }
    }

    public void take() throws InterruptedException {
        lock.lock();
        try{
            while (queue.isEmpty()){
                System.out.println("The queue is empty");
                itemsAvailable.await();
            }
            int val = queue.poll();
            System.out.println(STR."The value taken out from the queue is \{val}");
            spaceAvailable.signal();
        }finally {
            lock.unlock();
        }
    }
}

class Producer implements Runnable{
    SharedQueue sharedQueue;
    public Producer(SharedQueue sharedQueue){
        this.sharedQueue = sharedQueue;
    }
    @Override
    public void run() {
        int i = 0;
        while (true){
            try {
                sharedQueue.put(i++);
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Consumer implements Runnable{
    SharedQueue sharedQueue;
    public Consumer(SharedQueue sharedQueue){
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while(true){
            try {
                sharedQueue.take();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}