package org.example.multi.queue;

import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 5;

    public synchronized void put(int val) throws InterruptedException {
        if (queue.size() == CAPACITY){
            System.out.println("Queue is full. Producer is waiting...");
            wait();
        }
        queue.offer(val);
        System.out.println(STR."Produced: \{val}");
        notify();
    }

    public synchronized int take() throws InterruptedException {
        if (queue.isEmpty()){
            System.out.println("Queue is empty. Consumer is waiting...");
            wait();
        }
        int value = queue.poll();
        System.out.println(STR."Consumed: \{value}");
        notify();
        return value;
    }
}

class Producer implements Runnable{
    private final SharedQueue sharedQueue;
    public Producer(SharedQueue sharedQueue){
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        int i = 0;
        while (true){
            try {
                sharedQueue.put(i++);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Consumer implements Runnable{
    private final SharedQueue sharedQueue;
    public Consumer(SharedQueue sharedQueue){
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                System.out.println(sharedQueue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}