package org.example.dsa;

public class WaterContainer1 {
    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 100, 7};
        int right = height.length - 1;
        int left = 0;
        int max = 0;

        while (left < right) {
            int diff = right - left;
            int min = Math.min(height[left], height[right]);
            int product = min * diff;
            if (max < product) {
                max = product;
            }
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(max);
    }
}
