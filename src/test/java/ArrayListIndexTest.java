import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayListIndexTest {

    private ArrayListIndex<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayListIndex<>();
    }

    @Test
    public void testAddAtIndex() {
        System.out.println("Running testAddAtIndex...");

        list.addAtIndex(0, 1);
        assertEquals(1, list.size());
        assertEquals("[1, null, null, null, null, null, null, null, null]", Arrays.toString(list.getBackingArray()));

        list.addAtIndex(1, 2);
        assertEquals(2, list.size());
        assertEquals("[1, 2, null, null, null, null, null, null, null]", Arrays.toString(list.getBackingArray()));

        list.addAtIndex(1, 3);
        assertEquals(3, list.size());
        assertEquals("[1, 3, 2, null, null, null, null, null, null]", Arrays.toString(list.getBackingArray()));

        // Fill up the array to trigger resizing
        for (int i = 4; i <= 9; i++) {
            list.addAtIndex(list.size(), i);
        }
        assertEquals(9, list.size());
        assertEquals("[1, 3, 2, 4, 5, 6, 7, 8, 9]", Arrays.toString(list.getBackingArray()));

        list.addAtIndex(5, 10); // This should trigger resizing
        assertEquals(10, list.size());
        assertTrue(Arrays.toString(list.getBackingArray()).contains("null")); // New capacity should be double the old one
        assertEquals("[1, 3, 2, 4, 5, 10, 6, 7, 8, 9, null, null, null, null, null, null, null, null]", Arrays.toString(list.getBackingArray()));

        System.out.println("testAddAtIndex completed successfully.");
    }

    @Test
    public void testRemoveAtIndex() {
        System.out.println("Running testRemoveAtIndex...");

        for (int i = 1; i <= 9; i++) {
            list.addAtIndex(list.size(), i);
        }
        assertEquals(9, list.size());

        assertEquals(1, list.removeAtIndex(0));
        assertEquals(8, list.size());
        assertEquals("[2, 3, 4, 5, 6, 7, 8, 9, null]", Arrays.toString(list.getBackingArray()));

        assertEquals(5, list.removeAtIndex(3));
        assertEquals(7, list.size());
        assertEquals("[2, 3, 4, 6, 7, 8, 9, null, null]", Arrays.toString(list.getBackingArray()));

        assertEquals(9, list.removeAtIndex(6));
        assertEquals(6, list.size());
        assertEquals("[2, 3, 4, 6, 7, 8, null, null, null]", Arrays.toString(list.getBackingArray()));

        System.out.println("testRemoveAtIndex completed successfully.");
    }

    @Test
    public void testExceptions() {
        System.out.println("Running testExceptions...");

        assertThrows(IllegalArgumentException.class, () -> {
            list.addAtIndex(0, null);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.addAtIndex(-1, 1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.addAtIndex(1, 1);
        });

        assertThrows(NoSuchElementException.class, () -> {
            list.removeAtIndex(0);
        });

        list.addAtIndex(0, 1);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.removeAtIndex(-1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.removeAtIndex(1);
        });

        System.out.println("testExceptions completed successfully.");
    }

    @Test
    public void testComplexScenarios() {
        System.out.println("Running testComplexScenarios...");

        list.addAtIndex(0, 1);
        list.addAtIndex(1, 2);
        list.addAtIndex(2, 3);
        list.addAtIndex(1, 4);

        assertEquals(4, list.size());
        assertEquals("[1, 4, 2, 3, null, null, null, null, null]", Arrays.toString(list.getBackingArray()));

        assertEquals(4, list.removeAtIndex(1));
        assertEquals(3, list.size());
        assertEquals("[1, 2, 3, null, null, null, null, null, null]", Arrays.toString(list.getBackingArray()));

        assertEquals(3, list.removeAtIndex(2));
        assertEquals(2, list.size());
        assertEquals("[1, 2, null, null, null, null, null, null, null]", Arrays.toString(list.getBackingArray()));

        list.addAtIndex(1, 5);
        assertEquals(3, list.size());
        assertEquals("[1, 5, 2, null, null, null, null, null, null]", Arrays.toString(list.getBackingArray()));

        System.out.println("testComplexScenarios completed successfully.");
    }
}
