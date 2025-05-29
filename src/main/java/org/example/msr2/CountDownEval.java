package org.example.msr2;

public class CountDownEval {
    public static void main(String[] args) {
        CountDownLatchImpl latch = new CountDownLatchImpl(3);
        Runnable worker = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " waiting");
                latch.await();
                System.out.println(Thread.currentThread().getName() + " released");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        new Thread(worker).start();
        new Thread(worker).start();

        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
                latch.countDown();
                System.out.println("Countdown: " + latch.getCount());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
