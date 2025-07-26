package org.example.multi.queue1;

import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity = 5;

    public synchronized void put(int value) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("Queue size is full");
            wait();
        }
        queue.offer(value);
        System.out.println(STR."The value inputted is \{value}");
        notify();
    }

    public synchronized int take() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("The queue is empty");
            wait();
        }
        int value = queue.poll();
        System.out.println(STR."The value taken is \{value}");
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
                sharedQueue.take();
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}