import java.util.NoSuchElementException;

public class AVL<T extends Comparable<? super T>> {

    private AVLNode<T> root;
    private int size;

    /**
     * Adds the element to the tree.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        root = addHelper(root, data);
    }

    private AVLNode<T> addHelper(AVLNode<T> node, T data) {
        if (node == null) {
            size++;
            return new AVLNode<>(data);
        }

        int compareResult = data.compareTo(node.getData());
        if (compareResult < 0) {
            node.setLeft(addHelper(node.getLeft(), data));
        } else if (compareResult > 0) {
            node.setRight(addHelper(node.getRight(), data));
        } else {
            return node;
        }

        return balance(node);
    }

    /**
     * Removes and returns the element from the tree matching the given parameter.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If the data is null.
     * @throws java.util.NoSuchElementException   If the data is not found.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        AVLNode<T> dummy = new AVLNode<>(null);
        root = removeHelper(root, data, dummy);
        if (dummy.getData() == null) {
            throw new NoSuchElementException("Data not found in the tree");
        }
        size--;
        return dummy.getData();
    }

    private AVLNode<T> removeHelper(AVLNode<T> node, T data, AVLNode<T> dummy) {
        if (node == null) {
            return null;
        }

        int compareResult = data.compareTo(node.getData());
        if (compareResult < 0) {
            node.setLeft(removeHelper(node.getLeft(), data, dummy));
        } else if (compareResult > 0) {
            node.setRight(removeHelper(node.getRight(), data, dummy));
        } else {
            dummy.setData(node.getData());
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                AVLNode<T> successorParent = node;
                AVLNode<T> successor = node.getRight();
                while (successor.getLeft() != null) {
                    successorParent = successor;
                    successor = successor.getLeft();
                }
                node.setData(successor.getData());
                if (successorParent == node) {
                    node.setRight(successor.getRight());
                } else {
                    successorParent.setLeft(successor.getRight());
                }
            }
        }

        return balance(node);
    }

    /**
     * Updates the height and balance factor of a node using its
     * setter methods.
     *
     * @param currentNode The node to update the height and balance factor of.
     */
    public void updateHeightAndBF(AVLNode<T> currentNode) {
        int leftHeight = (currentNode.getLeft() == null) ? -1 : currentNode.getLeft().getHeight();
        int rightHeight = (currentNode.getRight() == null) ? -1 : currentNode.getRight().getHeight();
        
        currentNode.setHeight(Math.max(leftHeight, rightHeight) + 1);
        currentNode.setBalanceFactor(leftHeight - rightHeight);
    }

    /**
     * Method that rotates a current node to the left.
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    public AVLNode<T> rotateLeft(AVLNode<T> currentNode) {
        AVLNode<T> rightChild = currentNode.getRight();
        currentNode.setRight(rightChild.getLeft());
        rightChild.setLeft(currentNode);
        
        updateHeightAndBF(currentNode);
        updateHeightAndBF(rightChild);
        
        return rightChild;
    }

    /**
     * Method that rotates a current node to the right.
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    public AVLNode<T> rotateRight(AVLNode<T> currentNode) {
        AVLNode<T> leftChild = currentNode.getLeft();
        currentNode.setLeft(leftChild.getRight());
        leftChild.setRight(currentNode);
        
        updateHeightAndBF(currentNode);
        updateHeightAndBF(leftChild);
        
        return leftChild;
    }

    /**
     * Method that balances out the tree starting at the node passed in.
     *
     * @param currentNode The current node under inspection.
     * @return The AVLNode that the caller should return.
     */
    public AVLNode<T> balance(AVLNode<T> currentNode) {
        updateHeightAndBF(currentNode);
    
        if (currentNode.getBalanceFactor() > 1) {
            if (currentNode.getLeft().getBalanceFactor() < 0) {
                currentNode.setLeft(rotateLeft(currentNode.getLeft()));
            }
            return rotateRight(currentNode);
        } else if (currentNode.getBalanceFactor() < -1) {
            if (currentNode.getRight().getBalanceFactor() > 0) {
                currentNode.setRight(rotateRight(currentNode.getRight()));
            }
            return rotateLeft(currentNode);
        }
    
        return currentNode;
    }

    /**
     * Returns the root of the tree.
     *
     * @return The root of the tree.
     */
    public AVLNode<T> getRoot() {
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * @return The size of the tree.
     */
    public int size() {
        return size;
    }
}