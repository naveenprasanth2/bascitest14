package org.example.tesco.threading;

public class SharedQueueEval {
    public static void main(String[] args) {
        SharedQueue sharedQueue = new SharedQueue(5);
        Producer producer = new Producer(sharedQueue);
        Consumer consumer = new Consumer(sharedQueue);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
