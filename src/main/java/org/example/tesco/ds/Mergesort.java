package org.example.tesco.ds;

import java.util.Arrays;

public class Mergesort {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 2, 1, 2, 3, 4, 3, 4, 5, 55, 5, 4, 3};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    private static void sort(int[] a) {
        if (a.length == 1) return;

        int mid = a.length / 2;

        int[] left = new int[mid];
        int[] right = new int[a.length - mid];

        System.arraycopy(a, 0, left, 0, mid);
        System.arraycopy(a, mid, right, 0, right.length);
        sort(left);
        sort(right);
        merge(a, left, right);
    }

    private static void merge(int[] a, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                a[k++] = left[i++];
            } else {
                a[k++] = right[j++];
            }
        }

        while (i < left.length) {
            a[k++] = left[i++];
        }

        while (j < right.length) {
            a[k++] = right[j++];
        }
    }
}
