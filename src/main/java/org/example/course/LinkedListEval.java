//package org.example.course;
//
//public class LinkedListEval {
//    Node head;
//    Node tail;
//
//    public static void main(String[] args) {
//        LinkedListEval linkedListEval = new LinkedListEval();
//        linkedListEval.insert(1, 0);
//        linkedListEval.add(5);
//        linkedListEval.add(10);
//        linkedListEval.add(11);
//        linkedListEval.add(12);
//        linkedListEval.printNodes();
//    }
//
//    public void deleteNodeAtFront() {
//        if (head != null) {
//            head = head.next;
//        }
//    }
//
//    public void add(int value) {
//        Node node = new Node(value);
//        if (head == null && tail == null) {
//            head = tail = node;
//        } else {
//            tail.next = node;
//            tail = node;
//        }
//    }
//
//    public void insert(int value, int position) {
//        Node node = new Node(value);
//        if (head == null && tail == null) {
//            head = tail = node;
//            return;
//        }
//        Node current = node;
//        int count = 0;
//        while (count <= position) {
//            current = current.next;
//            count++;
//        }
//        Node temp = current.next;
//        current.next = node;
//        node.next = temp;
//    }
//
//    public void printNodes() {
//        Node current = head;
//        while (current != null) {
//            System.out.println(current.value);
//            current = current.next;
//        }
//    }
//
//    public void printNodesOneBefore() {
//        Node current = head;
//        while (current.next != null) {
//            System.out.println(current.value);
//            current = current.next;
//        }
//    }
//}
