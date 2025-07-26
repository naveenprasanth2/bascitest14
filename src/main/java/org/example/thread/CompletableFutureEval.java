package org.example.thread;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureEval {
    public static void main(String[] args) {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Naveen");
        });
        completableFuture.join();
    }
}
