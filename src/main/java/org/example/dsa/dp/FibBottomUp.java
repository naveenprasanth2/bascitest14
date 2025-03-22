package org.example.dsa.dp;

import java.util.Arrays;

public class FibBottomUp {
    static int[] memo;

    public FibBottomUp(int n) {
        memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = 1;
    }

    private void fib(int n){
        for (int i = 2; i <= n; i++){
            memo[i] = memo[i - 1] + memo[i - 2];
        }
    }


    public static void main(String[] args) {
        int n = 40;
        FibBottomUp fibBottomUp = new FibBottomUp(n);
        fibBottomUp.fib(n);
        System.out.println(Arrays.toString(memo));
    }
}
