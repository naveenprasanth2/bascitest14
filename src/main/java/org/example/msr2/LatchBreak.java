package org.example.msr2;

public class LatchBreak {
    private int count;

    public LatchBreak(int count) {
        this.count = count;
    }

    public synchronized void await() throws InterruptedException {
        if (count > 0) {
            wait();
        }
    }

    public synchronized void countDown() {
        if (count > 0) {
            count--;
            if (count == 0) {
                notifyAll();
            }
        }
    }

    public int getCount() {
        return count;
    }
}


