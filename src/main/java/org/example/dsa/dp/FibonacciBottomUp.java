package org.example.dsa.dp;

import java.util.Arrays;

public class FibonacciBottomUp {
    int[] memo;

    public FibonacciBottomUp(int n) {
        this.memo = new int[n + 1];
        memo[1] = 1;
    }

    private void fibonacci(int n){
        for (int i = 2; i <= n; i++){
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        System.out.println(Arrays.toString(memo));
    }

    public static void main(String[] args) {
        int n = 7;
        FibonacciBottomUp fibonacciTBottomUp = new FibonacciBottomUp(n);
        fibonacciTBottomUp.fibonacci(n);
    }
}
