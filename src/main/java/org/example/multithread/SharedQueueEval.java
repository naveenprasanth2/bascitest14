package org.example.multithread;

public class SharedQueueEval {
    public static void main(String[] args) {
        SharedQueue sharedQueue = new SharedQueue();
        Producer producer = new Producer(sharedQueue);
        Consumer consumer = new Consumer(sharedQueue);
        Thread p = new Thread(producer);
        Thread c = new Thread(consumer);
        p.start();
        c.start();
    }
}
