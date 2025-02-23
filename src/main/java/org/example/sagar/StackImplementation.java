package org.example.sagar;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class StackImplementation {
    int[] a;
    int pointer = 0;

    public StackImplementation(int size) {
        a = new int[size];
    }

    private void copy() {
        int[] newArray = new int[a.length + a.length];
        System.arraycopy(a, 0, newArray, 0, a.length);
        a = newArray;
    }

    private void shrink() {
        int[] newArray = new int[a.length / 2];
        System.arraycopy(a, 0, newArray, 0, a.length / 2);
        a = newArray;
    }

    private void push(int value) {
        System.out.println(pointer);
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
        if (pointer <= a.length / 2){
            shrink();
        }
        return tempValue;
    }

    public static void main(String[] args) {
        StackImplementation stack = new StackImplementation(1);
        stack.push(5);
        System.out.println(Arrays.toString(stack.a));
        stack.push(10);
        System.out.println(Arrays.toString(stack.a));
        stack.push(15);
        System.out.println(Arrays.toString(stack.a));
        stack.pop();
        System.out.println(Arrays.toString(stack.a));
    }
}
