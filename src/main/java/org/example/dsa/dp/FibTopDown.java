package org.example.dsa.dp;

public class FibTopDown {
    Integer[] memo;

    public FibTopDown(int n) {
        memo = new Integer[n + 1];
        memo[0] = 0;
        memo[1] = 1;
    }

    private int fib(int n){
        if (memo[n] != null){
            return memo[n];
        }
        memo[n] = fib(n -1) + fib(n - 2);
        return memo[n];
    }


    public static void main(String[] args) {
        int n = 40;
        FibTopDown fibTopDown = new FibTopDown(n);
        System.out.println(fibTopDown.fib(n));
    }
}
