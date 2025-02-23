package org.example.sagar;

public class MaximumSumOfTwoValues {
    public static void main(String[] args) {
        int[] a = {12, -4, 6, -2, 9, -8, 15, -3, 7, -1};
        int maxSum = a[0] + a[1];
        int currentSum = maxSum;
        for(int i = 2; i < a.length; i++){
            currentSum = ((currentSum) + a[i]) - (a[i - 2]);
            maxSum = Math.max(currentSum, maxSum);
            System.out.println(STR."\{maxSum} \{a[i - 2]} \{a[i - 1]} \{a[i]}");
        }
        System.out.println(maxSum);
    }
}
