import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

public class AVLTest {

    private AVL<Integer> avl;

    @BeforeEach
    public void setUp() {
        avl = new AVL<>();
    }

    @Test
    public void testUpdateHeightAndBF() {
        AVLNode<Integer> node = new AVLNode<>(5);
        node.setLeft(new AVLNode<>(3));
        node.setRight(new AVLNode<>(7));

        avl.updateHeightAndBF(node);

        assertEquals(1, node.getHeight());
        assertEquals(0, node.getBalanceFactor());
    }

    @Test
    public void testRotateLeft() {
        AVLNode<Integer> root = new AVLNode<>(5);
        root.setRight(new AVLNode<>(7));
        root.getRight().setRight(new AVLNode<>(9));

        AVLNode<Integer> newRoot = avl.rotateLeft(root);

        assertEquals(Integer.valueOf(7), newRoot.getData());
        assertEquals(Integer.valueOf(5), newRoot.getLeft().getData());
        assertEquals(Integer.valueOf(9), newRoot.getRight().getData());
    }

    @Test
    public void testRotateRight() {
        AVLNode<Integer> root = new AVLNode<>(5);
        root.setLeft(new AVLNode<>(3));
        root.getLeft().setLeft(new AVLNode<>(1));

        AVLNode<Integer> newRoot = avl.rotateRight(root);

        assertEquals(Integer.valueOf(3), newRoot.getData());
        assertEquals(Integer.valueOf(1), newRoot.getLeft().getData());
        assertEquals(Integer.valueOf(5), newRoot.getRight().getData());
    }

    @Test
    public void testBalance() {
        AVLNode<Integer> root = new AVLNode<>(5);
        root.setLeft(new AVLNode<>(3));
        root.getLeft().setLeft(new AVLNode<>(1));
    
        AVLNode<Integer> balancedRoot = avl.balance(root);
    
        assertEquals(Integer.valueOf(3), balancedRoot.getData());
        assertEquals(Integer.valueOf(1), balancedRoot.getLeft().getData());
        assertEquals(Integer.valueOf(5), balancedRoot.getRight().getData());
    }

    @Test
    public void testAdd() {
        avl.add(5);
        avl.add(3);
        avl.add(7);
        avl.add(1);
        avl.add(9);

        assertEquals(5, avl.size());
        assertEquals(Integer.valueOf(5), avl.getRoot().getData());
        assertEquals(Integer.valueOf(3), avl.getRoot().getLeft().getData());
        assertEquals(Integer.valueOf(7), avl.getRoot().getRight().getData());
        assertEquals(Integer.valueOf(1), avl.getRoot().getLeft().getLeft().getData());
        assertEquals(Integer.valueOf(9), avl.getRoot().getRight().getRight().getData());
    }

    @Test
    public void testAddDuplicate() {
        avl.add(5);
        avl.add(5);

        assertEquals(1, avl.size());
        assertEquals(Integer.valueOf(5), avl.getRoot().getData());
    }

    @Test
    public void testRemove() {
        avl.add(5);
        avl.add(3);
        avl.add(7);
        avl.add(1);
        avl.add(9);

        Integer removed = avl.remove(3);

        assertEquals(Integer.valueOf(3), removed);
        assertEquals(4, avl.size());
        assertEquals(Integer.valueOf(5), avl.getRoot().getData());
        assertEquals(Integer.valueOf(1), avl.getRoot().getLeft().getData());
        assertEquals(Integer.valueOf(7), avl.getRoot().getRight().getData());
        assertEquals(Integer.valueOf(9), avl.getRoot().getRight().getRight().getData());
    }

    @Test
    public void testRemoveNonExistent() {
        avl.add(5);
        avl.add(3);
        avl.add(7);

        assertThrows(NoSuchElementException.class, () -> avl.remove(9));
    }

    @Test
    public void testAddNull() {
        assertThrows(IllegalArgumentException.class, () -> avl.add(null));
    }

    @Test
    public void testRemoveNull() {
        assertThrows(IllegalArgumentException.class, () -> avl.remove(null));
    }
}