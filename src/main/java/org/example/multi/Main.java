package org.example.multi;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            throw new RuntimeException("Intentional Exception");
        });
        thread.setName("Misbehaving Thread");
        thread.setUncaughtExceptionHandler((t, e) -> System.out.println(STR."A critical error happened in \{t.getName()} and \{e.getMessage()}"));
        thread.start();
    }
}
