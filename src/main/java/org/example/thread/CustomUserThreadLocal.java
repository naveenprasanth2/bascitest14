package org.example.thread;

public class CustomUserThreadLocal {
    private static final ThreadLocal<String> currentUser = new ThreadLocal<>();


    public static void main(String[] args) {
        Runnable task1 = () -> {
            currentUser.set("Alice");
            sleepRandom();
            System.out.println(STR."\{Thread.currentThread().getName()} sees: \{currentUser.get()}");
            currentUser.remove();
        };

        Runnable task2 = () -> {
          currentUser.set("Bob");
          sleepRandom();
          System.out.println(STR."\{Thread.currentThread().getName()} sees: \{currentUser.get()}");
          currentUser.remove();
        };

        Runnable task3 = () -> {
            currentUser.set("Jane");
            sleepRandom();
            System.out.println(STR."\{Thread.currentThread().getName()} sees: \{currentUser.get()}");
            currentUser.remove();
        };

        new Thread(task1, "Thread-A").start();
        new Thread(task2, "Thread-B").start();
        new Thread(task3, "Thread-C").start();
    }



    private static void sleepRandom() {
        try {
            Thread.sleep((long) (Math.random() * 1000)); // Simulate variable delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
