package org.example.reflection;

import java.lang.ref.Cleaner;

public class CleaningSample {

    {
        Cleaner.create().register(this, () -> System.out.println("Garbage collected"));
    }

    public static void main(String[] args) throws InterruptedException {
        new CleaningSample();
        System.gc();
        Thread.sleep(10000);
    }
}
