package org.example.multithread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Semaphores {
    public final Queue<Integer> queue = new LinkedList<>();
    public final Semaphore spaces = new Semaphore(1);
    public final Semaphore items = new Semaphore(0);

    public static void main(String[] args) {
        Semaphores semaphores = new Semaphores();
        new Thread(new Producer1(semaphores)).start();
        new Thread(new Consumer1(semaphores)).start();
    }
}

class Producer1 implements Runnable {
    Semaphores sem;

    public Producer1(Semaphores sem) {
        this.sem = sem;
    }

    @Override
    public void run() {
        int value = 0;
        while (true) {
            try {
                sem.spaces.acquire();
                Thread.sleep(200);
                sem.queue.offer(value++);
                System.out.println(STR."Value added \{value}");
                sem.items.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Consumer1 implements Runnable {
    Semaphores sem;

    public Consumer1(Semaphores sem) {
        this.sem = sem;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sem.items.acquire();
                Thread.sleep(500);
                System.out.println(STR."Value taken out is \{sem.queue.poll()}");
                sem.spaces.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
