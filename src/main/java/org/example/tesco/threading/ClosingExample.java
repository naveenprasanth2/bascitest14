package org.example.tesco.threading;

public class ClosingExample {
    public static void main(String[] args) throws Exception {
        try (TryClose tryClose = new TryClose()) {
            tryClose.test();
            System.out.println("Inside try");
        } catch (Exception _) {
            System.out.println("Its in catch block");
        } finally {
            System.out.println("Inside finally");
        }
    }
}
