package org.example.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {55, 1, 3, 2, 1, 2, 3, 4, 5, 4, 3, 4, 5};
        System.out.println(STR."The array before sorting is : \{Arrays.toString(arr)}");
        mergeSort(arr);
        System.out.println(STR."The array after sorting is : \{Arrays.toString(arr)}");
    }

    private static void mergeSort(int[] original) {
        if (original.length == 1) return;
        int length = original.length;
        int lSize = length / 2;
        int[] left = new int[lSize];
        int[] right = new int[length - lSize];
        System.arraycopy(original, 0, left, 0, lSize);
        System.arraycopy(original, lSize, right, 0, right.length);
        mergeSort(left);
        mergeSort(right);
        merge(original, left, right);
    }

    private static void merge(int[] original, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                original[k++] = left[i++];
            } else {
                original[k++] = right[j++];
            }
        }

        while (i < left.length) {
            original[k++] = left[i++];
        }

        while (j < right.length) {
            original[k++] = right[j++];
        }
    }
}
