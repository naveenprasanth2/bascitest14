package org.example.dsa.dp;

public class FibonacciTopDown {
    Integer[] memo;

    public FibonacciTopDown(int n) {
        this.memo = new Integer[n + 1];
    }

    private int fib(int n) {
        if (memo[n] != null) {
            return memo[n];
        }
        if (n == 0 || n == 1) {
            return n;
        }
        memo[n] = fib(n - 1) + fib(n - 2);
        return memo[n];
    }

    public static void main(String[] args) {
        FibonacciTopDown fibonacciTopDown = new FibonacciTopDown(40);
        System.out.println(fibonacciTopDown.fib(7));
    }
}
