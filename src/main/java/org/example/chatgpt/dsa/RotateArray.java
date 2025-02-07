package org.example.chatgpt.dsa;

import java.util.Arrays;

class RotateArray {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        rotateArray(nums, 0, n - 1);
        rotateArray(nums, 0, k - 1);
        rotateArray(nums, k, n - 1);
        System.out.println(Arrays.toString(nums));
    }

    private static void rotateArray(int[] num, int left, int right) {
        while (left < right) {
            int temp = num[left];
            num[left] = num[right];
            num[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        RotateArray array = new RotateArray();
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        array.rotate(a, 3);
    }
}