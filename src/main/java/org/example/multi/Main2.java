package org.example.multi;

import java.util.Random;

public class Main2 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Power();
        Thread thread1 = new Power();
        thread.start();
        thread1.start();
        thread.join();
        System.out.println("Thread 0 joined");
        thread1.join();
        System.out.println("Thread 1 joined");
        System.out.println("Main thread complete");
    }

    private static class Power extends Thread{
        @Override
        public void run() {
            try {
                int val = new Random().nextInt(5000, 10000);
                System.out.println(STR."\{val} \{Thread.currentThread().getName()}");
                Thread.sleep(new Random().nextInt(5000, 10000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
