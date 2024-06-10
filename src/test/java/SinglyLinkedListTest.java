import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SinglyLinkedListTest {

    private SinglyLinkedList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new SinglyLinkedList<>();
    }

    @Test
    public void testAddToFront() {
        System.out.println("Running testAddToFront...");
        list.addToFront(1);
        assertEquals(1, list.size());
        assertEquals(1, list.getHead().getData());
        assertEquals(1, list.getTail().getData());

        list.addToFront(2);
        assertEquals(2, list.size());
        assertEquals(2, list.getHead().getData());
        assertEquals(1, list.getTail().getData());
        System.out.println("testAddToFront completed successfully.");
    }

    @Test
    public void testAddToBack() {
        System.out.println("Running testAddToBack...");
        list.addToBack(1);
        assertEquals(1, list.size());
        assertEquals(1, list.getHead().getData());
        assertEquals(1, list.getTail().getData());

        list.addToBack(2);
        assertEquals(2, list.size());
        assertEquals(1, list.getHead().getData());
        assertEquals(2, list.getTail().getData());
        System.out.println("testAddToBack completed successfully.");
    }

    @Test
    public void testRemoveFromFront() {
        System.out.println("Running testRemoveFromFront...");
        list.addToFront(1);
        list.addToFront(2);
        list.addToFront(3);

        assertEquals(3, list.removeFromFront());
        assertEquals(2, list.size());
        assertEquals(2, list.getHead().getData());
        assertEquals(1, list.getTail().getData());

        assertEquals(2, list.removeFromFront());
        assertEquals(1, list.size());
        assertEquals(1, list.getHead().getData());
        assertEquals(1, list.getTail().getData());

        assertEquals(1, list.removeFromFront());
        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());
        System.out.println("testRemoveFromFront completed successfully.");
    }

    @Test
    public void testRemoveFromBack() {
        System.out.println("Running testRemoveFromBack...");
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);

        assertEquals(3, list.removeFromBack());
        assertEquals(2, list.size());
        assertEquals(1, list.getHead().getData());
        assertEquals(2, list.getTail().getData());

        assertEquals(2, list.removeFromBack());
        assertEquals(1, list.size());
        assertEquals(1, list.getHead().getData());
        assertEquals(1, list.getTail().getData());

        assertEquals(1, list.removeFromBack());
        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());
        System.out.println("testRemoveFromBack completed successfully.");
    }

    @Test
    public void testExceptions() {
        System.out.println("Running testExceptions...");
        assertThrows(IllegalArgumentException.class, () -> {
            list.addToFront(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            list.addToBack(null);
        });

        assertThrows(NoSuchElementException.class, () -> {
            list.removeFromFront();
        });

        assertThrows(NoSuchElementException.class, () -> {
            list.removeFromBack();
        });
        System.out.println("testExceptions completed successfully.");
    }
}
