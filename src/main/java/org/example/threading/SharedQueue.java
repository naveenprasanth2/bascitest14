package org.example.threading;

import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {
    int capacity = 5;
    Queue<Integer> queue = new LinkedList<>();

    public synchronized void put(int val) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println(STR."Waiting for consumer \{val}");
            wait();
        }
        queue.offer(val);
        notify();
    }

    public synchronized void take() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Waiting for producer");
            wait();
        }
        int val = queue.poll();
        System.out.println(STR."Value consumed is \{val}");
        notify();
    }
}

class Producer implements Runnable {
    SharedQueue queue;

    public Producer(SharedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            try {
                Thread.sleep(200);
                queue.put(i++);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Consumer implements Runnable {
    SharedQueue queue;

    public Consumer(SharedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
                queue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}