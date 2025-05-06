package org.example.random;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
    private int val = 0;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private void read() {
        lock.readLock().lock();
        try {
            System.out.println(STR."\{Thread.currentThread().getName()} read value: \{val}");
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
        }
    }

    private void write(int value) {
        lock.writeLock().lock();
        try {
            val = value;
            System.out.println(STR."\{Thread.currentThread().getName()} writing value: \{value}");
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLock data = new ReadWriteLock();
        Runnable reader = () -> {
            for (int i = 0; i < 3; i++) {
                data.read();
            }
        };
        Runnable writer = () -> {
            for (int i = 0; i < 2; i++) {
                data.write((int) (Math.random() * 100));
            }
        };

        new Thread(reader, "Reader-1").start();
        new Thread(reader, "Reader-2").start();
        new Thread(writer, "Writer-1").start();
        new Thread(reader, "Reader-3").start();
        new Thread(writer, "Writer-2").start();
    }
}


