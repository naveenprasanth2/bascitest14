package org.example.multithread;

import java.lang.ref.Cleaner;

public class CleanEval {
    private static final Cleaner cleaner = Cleaner.create();
    private final Cleaner.Cleanable cleanable;

    public CleanEval(){
        cleanable = cleaner.register(this, () -> System.out.println("test"));
    }

    public static void main(String[] args) throws InterruptedException {
        new CleanEval();
        // Suggest GC to trigger cleanup (not guaranteed immediately)
        System.gc();
        Thread.sleep(1000); // Give time for cleaner thread to run
    }
}
