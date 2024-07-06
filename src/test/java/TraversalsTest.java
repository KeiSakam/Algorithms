import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TraversalsTest {

    private Traversals<Integer> traversals;
    private TreeNode<Integer> root;

    @BeforeEach
    public void setUp() {
        traversals = new Traversals<>();
        root = new TreeNode<>(50);
        TreeNode<Integer> node25 = new TreeNode<>(25);
        TreeNode<Integer> node100 = new TreeNode<>(100);
        TreeNode<Integer> node10 = new TreeNode<>(10);
        TreeNode<Integer> node75 = new TreeNode<>(75);
        TreeNode<Integer> node125 = new TreeNode<>(125);
        TreeNode<Integer> node110 = new TreeNode<>(110);

        root.setLeft(node25);
        root.setRight(node100);
        node25.setLeft(node10);
        node100.setLeft(node75);
        node100.setRight(node125);
        node125.setLeft(node110);
    }

    @Test
    public void testPreorder() {
        List<Integer> expected = Arrays.asList(50, 25, 10, 100, 75, 125, 110);
        List<Integer> result = traversals.preorder(root);
        assertEquals(expected, result);
    }

    @Test
    public void testInorder() {
        List<Integer> expected = Arrays.asList(10, 25, 50, 75, 100, 110, 125);
        List<Integer> result = traversals.inorder(root);
        assertEquals(expected, result);
    }

    @Test
    public void testPostorder() {
        List<Integer> expected = Arrays.asList(10, 25, 75, 110, 125, 100, 50);
        List<Integer> result = traversals.postorder(root);
        assertEquals(expected, result);
    }
}
