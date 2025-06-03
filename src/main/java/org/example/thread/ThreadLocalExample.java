package org.example.thread;

import java.util.Random;

public class ThreadLocalExample {
    private static final ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        Runnable task = () -> {
            threadLocal.set(new Random().nextInt(1, 1000));
            System.out.println(STR."\{Thread.currentThread().getName()}: \{threadLocal.get()}");
        };

        Thread t1 = new Thread(task, "Thread-A");
        Thread t2 = new Thread(task, "Thread-B");

        t1.start();
        t2.start();
    }
}
