package org.example.chatgpt.dsa;

import java.util.Arrays;

public class RotateArrays {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        int k1 = 5;
        rotate(nums1, k1);
        System.out.println(Arrays.toString(nums1));
    }

    private static void rotate(int[] nums, int k1) {
        int length = nums.length;
        k1 %= length;
        rotateArray(nums, 0, length - 1);
        rotateArray(nums, 0, k1 - 1);
        rotateArray(nums, k1, length - 1);
    }

    private static void rotateArray(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[end];
            nums[end] = nums[start];
            nums[start] = temp;
            start++;
            end--;
        }
    }

}
