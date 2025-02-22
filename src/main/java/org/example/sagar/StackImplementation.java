package org.example.sagar;

public class StackImplementation {
    int size = 1;
    int[] a = new int[size];
    int pointer = 0;

    private void copy() {
        int[] newArray = new int[a.length + a.length];
        System.arraycopy(a, 0, newArray, 0, a.length);
        a = newArray;
    }

    private void push(int value) {
        if (pointer >= a.length) {
            copy();
        }
        a[pointer++] = value;
    }

    private int peek() {
        return a[pointer - 1];
    }

    private int pop() {
        int tempValue = a[pointer - 1];
        a[pointer - 1] = 0;
        pointer--;
        return tempValue;
    }

    public static void main(String[] args) {
        StackImplementation stack = new StackImplementation();
        stack.push(5);
        stack.push(10);
        stack.push(15);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        stack.push(100);
        System.out.println(stack.peek());
    }
}
