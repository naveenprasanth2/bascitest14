package org.example.sagar;

import java.util.Arrays;

public class PutZerosToEnd {
    public static void main(String[] args) {
        int[] a = {2, 3, 0, 0, 5, 0, 8};
        int left = 0;
        int index = 0;
        while (left < a.length) {
            if (a[left] != 0){
                int temp = a[left];
                a[left] = a[index];
                a[index] = temp;
                index++;
            }
            left++;
        }

        System.out.println(Arrays.toString(a));
    }
}
