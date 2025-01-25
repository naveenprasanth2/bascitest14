package org.example.dsa;

public class WaterContainer {
    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 100, 7}; // Example input array
        int max = 0;

        for (int i = 0; i < height.length; i++) {
            int initial = height[i];
            for (int j = i + 1; j < height.length; j++) {
                int minValue = Math.min(initial, height[j]);
                int product = (minValue * (j - i));
                if (product > max){
                    max = product;
                }
            }
        }

        System.out.println(max);
    }
}
