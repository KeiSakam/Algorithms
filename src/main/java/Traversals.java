import java.util.List;
import java.util.ArrayList;

/**
 * Your implementation of the pre-order, in-order, and post-order
 * traversals of a tree.
 */
public class Traversals<T extends Comparable<? super T>> {

    /**
     * DO NOT ADD ANY GLOBAL VARIABLES!
     */

    /**
     * Given the root of a binary search tree, generate a
     * pre-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the pre-order traversal of the tree.
     */
    public List<T> preorder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private void preorderHelper(TreeNode<T> node, List<T> result) {
        if (node != null) {
            result.add(node.getData());
            preorderHelper(node.getLeft(), result);
            preorderHelper(node.getRight(), result);
        }
    }

    /**
     * Given the root of a binary search tree, generate an
     * in-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the in-order traversal of the tree.
     */
    public List<T> inorder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(TreeNode<T> node, List<T> result) {
        if (node != null) {
            inorderHelper(node.getLeft(), result);
            result.add(node.getData());
            inorderHelper(node.getRight(), result);
        }
    }

    /**
     * Given the root of a binary search tree, generate a
     * post-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the post-order traversal of the tree.
     */
    public List<T> postorder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private void postorderHelper(TreeNode<T> node, List<T> result) {
        if (node != null) {
            postorderHelper(node.getLeft(), result);
            postorderHelper(node.getRight(), result);
            result.add(node.getData());
        }
    }
}

/**
 * Node class used for implementing the BST.
 *
 * DO NOT MODIFY THIS FILE!!
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
class TreeNode<T extends Comparable<? super T>> {

    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    /**
     * Constructs a TreeNode with the given data.
     *
     * @param data the data stored in the new node
     */
    TreeNode(T data) {
        this.data = data;
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    T getData() {
        return data;
    }

    /**
     * Gets the left child.
     *
     * @return the left child
     */
    TreeNode<T> getLeft() {
        return left;
    }

    /**
     * Gets the right child.
     *
     * @return the right child
     */
    TreeNode<T> getRight() {
        return right;
    }

    /**
     * Sets the data.
     *
     * @param data the new data
     */
    void setData(T data) {
        this.data = data;
    }

    /**
     * Sets the left child.
     *
     * @param left the new left child
     */
    void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    /**
     * Sets the right child.
     *
     * @param right the new right child
     */
    void setRight(TreeNode<T> right) {
        this.right = right;
    }
}
