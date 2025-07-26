package org.example.practise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CounterTest {
    public static void main(String[] args) {
        ExecutorService executors = Executors.newFixedThreadPool(10);
        CounterExample counterExample = new CounterExample();
        List<CounterRun> counterRunList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            counterRunList.add(new CounterRun(counterExample));
        }
        for (CounterRun counterRun : counterRunList) {
            executors.submit(counterRun);
        }
        System.out.println(counterExample.count);
    }
}


class CounterRun implements Runnable {
    private final CounterExample counterExample;

    public CounterRun(CounterExample counterExample) {
        this.counterExample = counterExample;
    }

    @Override
    public void run() {
        counterExample.increaseCount();
    }
}