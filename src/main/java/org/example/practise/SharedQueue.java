package org.example.practise;

import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {
    Queue<Integer> queue = new LinkedList<>();;
    private final int MAX_CAPACITY = 5;

    public synchronized void put(int val) throws InterruptedException {
        while (queue.size() >= MAX_CAPACITY){
            System.out.println("Queue is full");
            wait();
        }
        queue.offer(val);
        notify();
    }

    public synchronized void take() throws InterruptedException {
        while (queue.isEmpty()){
            System.out.println("The queue is empty");
            wait();
        }
        System.out.println(queue.poll());
        notify();
    }
}

class P implements Runnable{
    SharedQueue sharedQueue;
    public P(SharedQueue sharedQueue){
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        int i = 0;
        while (true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                sharedQueue.put(i++);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class C implements Runnable{
    SharedQueue sharedQueue;
    public C(SharedQueue sharedQueue){
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(500);
                sharedQueue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}