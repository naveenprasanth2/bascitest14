package org.example.practise;

public class FindMissing {
    static int[] arr = {1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12};

    public static void main(String[] args) {
        int start = 0, end = arr.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] > mid + 1) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(start + 1);
    }
}