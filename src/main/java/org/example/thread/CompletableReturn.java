package org.example.thread;

import java.util.concurrent.CompletableFuture;

public class CompletableReturn {
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Naveen");
        System.out.println(completableFuture.join());
    }
}
