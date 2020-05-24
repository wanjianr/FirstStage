package com.douye.tree;

public class AVLTree<E> extends BalanceBST<E> {
    private static class AVLNode<E> extends Node<E>{
        int height = 1;
        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor(){
            int leftHeight = this.left == null ? 0 : ((AVLNode<E>)this.left).height;
            int rightHeight = this.right == null ? 0 : ((AVLNode<E>)this.right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight(){
            int leftHeight = this.left == null ? 0 : ((AVLNode<E>)this.left).height;
            int rightHeight = this.right == null ? 0 : ((AVLNode<E>)this.right).height;
            height = 1 +  Math.max(leftHeight,rightHeight);
        }

        public Node<E> tallerChild(){
            int leftHeight = this.left == null ? 0 : ((AVLNode<E>)this.left).height;
            int rightHeight = this.right == null ? 0 : ((AVLNode<E>)this.right).height;
            if (leftHeight > rightHeight) return this.left;
            if (leftHeight < rightHeight) return this.right;
            return this.left == null ? this.right:this.left;
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }
            return element + "_p(" + parentString + ")_h(" + height + ")";
        }
    }

    private boolean isBalanced(Node<E> node){
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    private void updateHeight(Node<E> node){
        ((AVLNode<E>)node).updateHeight();
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);
        updateHeight(grand);
        updateHeight(parent);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null){
            if (isBalanced(node)){
                updateHeight(node);
            } else {
                rebalance(node);
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null){
            if (isBalanced(node)){
                updateHeight(node);
            } else {
                rebalance(node);
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    private void rebalance(Node<E> grand){
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parent).tallerChild();
        if (parent.isLeftChild()){ // L
            if (node.isLeftChild()){ // LL
                rotateRight(grand);
            } else { // LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else { // R
            if (node.isRightChild()){ // RR
                rotateLeft(grand);
            } else { // RL
                rotateRight(parent);
                rotateLeft(grand);
            }
        }
    }
}
