package org.example.tesco.threading;

public class CountDownLatch {
    private int count;

    public CountDownLatch(int count) {
        this.count = count;
    }

    public void await() throws InterruptedException {
        synchronized (this) {
            while (count > 0) {
                wait();
            }
        }
    }

    public void countDown() {
        synchronized (this) {
            if (count > 0) {
                count--;
            }
            if (count == 0) {
                notifyAll();
            }
        }
    }

    public int getCount() {
        return this.count;
    }

}
