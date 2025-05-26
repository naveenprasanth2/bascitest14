package org.example.reflection;

import java.lang.ref.Cleaner;

public class CleaningSample {
    private final Cleaner cleaner = Cleaner.create();
    private final Cleaner.Cleanable cleanable = cleaner.register(this, () -> System.out.println("Garbage collected"));

    public static void main(String[] args) throws InterruptedException {
        new CleaningSample();
        System.gc();
        Thread.sleep(10000);
    }
}
