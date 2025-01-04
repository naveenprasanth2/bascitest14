package org.example.tesco.threading;

public class Threading {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("summa");
        });
        Thread t2 = new Thread(() -> System.out.println("summa tha"));

        t1.start();
        t2.start();
        t1.join();
        System.out.println("completed");
    }
}
