package org.example.dsa;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] original = {1, 8, 6, 2, 5, 4, 8, 100, 7};
        System.out.println(STR."The original array is\{Arrays.toString(original)}");
        mergeSort(original);
        System.out.println(STR."The sorted array is\{Arrays.toString(original)}");
    }

    private static void mergeSort(int[] original) {
        if (original.length == 1) return;

        int mid = original.length / 2;
        int[] left = new int[mid];
        int[] right = new int[original.length - mid];

        System.arraycopy(original, 0, left, 0, mid);
        System.arraycopy(original, mid, right, 0, right.length);
        mergeSort(left);
        mergeSort(right);
        merge(left, right, original);
    }

    private static void merge(int[] left, int[] right, int[] original) {
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
