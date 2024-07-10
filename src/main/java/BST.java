import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    private BSTNode<T> root;
    private int size;

    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }
        root = addRecursive(root, data);
    }

    private BSTNode<T> addRecursive(BSTNode<T> node, T data) {
        if (node == null) {
            size++;
            return new BSTNode<>(data);
        }

        int cmp = data.compareTo(node.getData());
        if (cmp < 0) {
            node.setLeft(addRecursive(node.getLeft(), data));
        } else if (cmp > 0) {
            node.setRight(addRecursive(node.getRight(), data));
        }

        return node;
    }

    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }
        BSTNode<T> dummy = new BSTNode<>(null);
        root = removeRecursive(root, data, dummy);
        return dummy.getData();
    }

    private BSTNode<T> removeRecursive(BSTNode<T> node, T data, BSTNode<T> dummy) {
        if (node == null) {
            throw new NoSuchElementException("Data not found in the tree.");
        }

        int cmp = data.compareTo(node.getData());
        if (cmp < 0) {
            node.setLeft(removeRecursive(node.getLeft(), data, dummy));
        } else if (cmp > 0) {
            node.setRight(removeRecursive(node.getRight(), data, dummy));
        } else {
            dummy.setData(node.getData());
            size--;

            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                BSTNode<T> successorDummy = new BSTNode<>(null);
                node.setRight(removeSuccessor(node.getRight(), successorDummy));
                node.setData(successorDummy.getData());
            }
        }

        return node;
    }

    private BSTNode<T> removeSuccessor(BSTNode<T> node, BSTNode<T> dummy) {
        if (node.getLeft() == null) {
            dummy.setData(node.getData());
            return node.getRight();
        } else {
            node.setLeft(removeSuccessor(node.getLeft(), dummy));
            return node;
        }
    }

    public BSTNode<T> getRoot() {
        return root;
    }

    public int size() {
        return size;
    }
}
