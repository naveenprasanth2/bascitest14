package org.example.threading;

public class SharedQueueEval {
    public static void main(String[] args) {
        SharedQueue queue = new SharedQueue();
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        Thread p1 = new Thread(producer);
        Thread c1 = new Thread(consumer);
        p1.start();
        c1.start();
    }
}
