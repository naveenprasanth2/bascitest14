package org.example.random;

public class Random {
    private long seed;
    private int from;
    private int to;

    public Random(int from, int to) {
        if (from > to) {
            throw new IllegalArgumentException("from should be <= to");
        }
        this.from = from;
        this.to = to;
        seed = System.currentTimeMillis();
    }

    public long generateRandom() {
        seed += 31;
        seed %= 1000000;
        long result = seed % (from + (to - from));
        return result;
    }
}
