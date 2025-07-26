package org.example.multi.queue;

public class QueueEval {
    public static void main(String[] args) {
        SharedQueue sharedQueue = new SharedQueue();
        Producer producer = new Producer(sharedQueue);
        Consumer consumer = new Consumer(sharedQueue);

        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(consumer);

        thread1.start();
        thread2.start();
    }
}
