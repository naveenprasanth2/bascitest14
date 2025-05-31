package org.example.msr2;

public class LatchBreakEval {
    public static void main(String[] args) {
        LatchBreak latch = new LatchBreak(3);
        Runnable runnable = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " waiting");
                latch.await();
                System.out.println(Thread.currentThread().getName() + " released");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
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
