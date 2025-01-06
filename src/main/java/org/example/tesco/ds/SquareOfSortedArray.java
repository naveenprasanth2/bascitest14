package org.example.tesco.ds;

import java.util.Arrays;

public class SquareOfSortedArray {
    public static void main(String[] args) {
        int[] a = {-3, -1, -1, 0, 1, 2, 5};
        int left = 0;
        int right = a.length - 1;
        int[] result = new int[a.length];
        int resultPosition = result.length - 1;
        while (left < right){
            if (Math.abs(a[left]) <= Math.abs(a[right])){
                result[resultPosition--] = (int) Math.pow(a[right--], 2);
            }else{
                result[resultPosition--] = (int) Math.pow(a[left++], 2);
            }
        }

        System.out.println(Arrays.toString(result));
    }
}
