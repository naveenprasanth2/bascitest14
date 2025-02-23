package org.example.sagar;

public class Factorial {
    public static void main(String[] args) {
        System.out.println(findFactorial(5));
    }

    private static int findFactorial(int number) {
        if (number == 1) return 1;
        return number * findFactorial(number - 1);
    }
}
