package org.example.random;

public class RandomTest {
    public static void main(String[] args) {
        Random random = new Random(10, 100);
        for (int i = 0; i <= 10; i++){
            System.out.println(random.generateRandom());
        }
    }
}
