package org.example.dsa;

public class WaterContainer {
    public static void main(String[] args) {
        int[] height = {1, 8, 99, 6, 2, 5, 4, 8, 100, 7}; // Example input array
        int max = 0;
        int left = 0;
        int right = (height.length - 1);

        while (left < right) {
            int product = Math.min(height[right], height[left]);
            int dis = right - left;
            int finalProduct = product * dis;
            max = Math.max(max, finalProduct);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(max);
    }
}
