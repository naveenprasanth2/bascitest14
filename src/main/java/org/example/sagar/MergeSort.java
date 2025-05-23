package org.example.sagar;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] original = {100, 1, 2, 1, 2, 3, 4, 3, 2, 3, 4, 5, 5};
        System.out.println(STR."Original Array \{Arrays.toString(original)}");
        mergeSort(original);
        System.out.println(STR."Sorted Array \{Arrays.toString(original)}");
    }

    private static void mergeSort(int[] original) {
        int length = original.length;
        if (length == 1) return;
        int mid = length / 2;
        int[] left = new int[mid];
        int[] right = new int[length - mid];
        System.arraycopy(original, 0, left, 0, mid);
        System.arraycopy(original, mid, right, 0, right.length);
        mergeSort(left);
        mergeSort(right);
        merge(original, left, right);
    }

    private static void merge(int[] original, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
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
