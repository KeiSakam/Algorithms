import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import java.util.Arrays;

public class ArrayQueueTest {

    private ArrayQueue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new ArrayQueue<>();
    }

    @Test
    public void testEnqueue() {
        System.out.println("Running testEnqueue...");
        queue.enqueue(1);
        assertEquals(1, queue.size());
        assertEquals("[1, null, null, null, null, null, null, null, null]", Arrays.toString(queue.getBackingArray()));

        queue.enqueue(2);
        assertEquals(2, queue.size());
        assertEquals("[1, 2, null, null, null, null, null, null, null]", Arrays.toString(queue.getBackingArray()));

        // Fill up the array to trigger resizing
        for (int i = 3; i <= 9; i++) {
            queue.enqueue(i);
        }
        assertEquals(9, queue.size());
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9]", Arrays.toString(queue.getBackingArray()));

        queue.enqueue(10); // This should trigger resizing
        assertEquals(10, queue.size());
        assertTrue(Arrays.toString(queue.getBackingArray()).contains("null")); // New capacity should be double the old one
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, null, null, null, null, null, null, null, null]", Arrays.toString(queue.getBackingArray()));

        System.out.println("testEnqueue completed successfully.");
    }

    @Test
    public void testDequeue() {
        System.out.println("Running testDequeue...");
        for (int i = 1; i <= 9; i++) {
            queue.enqueue(i);
        }
        assertEquals(9, queue.size());

        assertEquals(1, queue.dequeue());
        assertEquals(8, queue.size());
        assertEquals("[null, 2, 3, 4, 5, 6, 7, 8, 9]", Arrays.toString(queue.getBackingArray()));

        queue.enqueue(10);
        assertEquals(9, queue.size());
        assertEquals(2, queue.dequeue());
        assertEquals(8, queue.size());

        System.out.println("testDequeue completed successfully.");
    }

    @Test
    public void testExceptions() {
        System.out.println("Running testExceptions...");
        assertThrows(IllegalArgumentException.class, () -> {
            queue.enqueue(null);
        });

        assertThrows(NoSuchElementException.class, () -> {
            queue.dequeue();
        });

        System.out.println("testExceptions completed successfully.");
    }
}
