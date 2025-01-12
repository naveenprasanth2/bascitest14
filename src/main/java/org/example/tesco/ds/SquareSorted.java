package org.example.tesco.ds;

import java.util.Arrays;

public class SquareSorted {
    public static void main(String[] args) {
        int[] a = {-25, -3, -1, -1, 0, 1, 2, 5};
        int left = 0;
        int right = a.length - 1;
        int[] sorted = new int[a.length];
        int index = sorted.length - 1;
        while (left < right) {
            if (Math.abs(a[left]) > Math.abs(a[right])) {
                sorted[index--] = (int) Math.pow(a[left++], 2);
            } else {
                sorted[index--] = (int) Math.pow(a[right--], 2);
            }
        }
        System.out.println(Arrays.toString(sorted));
    }
}
