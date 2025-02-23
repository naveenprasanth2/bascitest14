package org.example.dsaCourse.slidingWindow;

public class SumOfSuArrays {
    public static void main(String[] args) {
        int[] array = {2, 1, 5, 8, 9, 4, 6};
        int result = 0;
        int k = 3;
        for (int i = 0; i < k; i++) {
            result += array[i];
        }
        System.out.println(result);
        for (int i = k; i < array.length; i++) {
            result = result + array[i] - array[i - k];
            System.out.println(result);
        }
    }
}
