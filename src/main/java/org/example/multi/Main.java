package org.example.multi;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(STR."We are now in thread \{Thread.currentThread().getName()}");
            System.out.println(STR."We are now in thread with priority \{Thread.currentThread().getPriority()}");
        });
        thread.setName("New Worker Thread");
        thread.setPriority(Thread.MAX_PRIORITY);
        System.out.println(STR."We are in thread \{Thread.currentThread().getName()} before starting a new thread");
        thread.start();
        System.out.println(STR."We are in thread \{Thread.currentThread().getName()} before starting a new thread");
        Thread.sleep(2000);
    }
}
