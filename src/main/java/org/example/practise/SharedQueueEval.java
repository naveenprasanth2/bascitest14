package org.example.practise;

public class SharedQueueEval {
    public static void main(String[] args) {
        SharedQueue sharedQueue = new SharedQueue();
        P p = new P(sharedQueue);
        C c = new C(sharedQueue);
        new Thread(p).start();
        new Thread(c).start();
    }
}
