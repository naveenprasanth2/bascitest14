package org.example.tesco.threading;

import java.util.concurrent.*;

public class RunAndCall {

    static Runnable runnable = () -> System.out.println("Summa1");
    static Callable<Integer> callable = () -> 2;


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Integer> future;
        try (ExecutorService executorService = Executors.newFixedThreadPool(1)) {
            future = executorService.submit(callable);
            executorService.execute(runnable);
        }
        System.out.println(future.get());
    }
}
