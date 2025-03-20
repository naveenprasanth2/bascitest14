package org.example.dsa.tree;

public class BinaryTree {
    int[] tree;
    public BinaryTree(int[] tree){
        this.tree = tree;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private int parent(int i){
        return (i - 1) /  2;
    }


    private void preOrderTraversal(int nodeNo){
        if(nodeNo < this.tree.length){
            System.out.println(tree[nodeNo]);
            preOrderTraversal(left(nodeNo));
            preOrderTraversal(right(nodeNo));
        }
    }

    public void preOrderTraversalFromRootNode(){
        preOrderTraversal(0);
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree(new int[]{10, 20, 30, 40, 50, 60, 70, 80});
        binaryTree.preOrderTraversal(0);
    }
}
