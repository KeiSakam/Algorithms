import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

public class MaxHeapTest {
    private MaxHeap<Integer> maxHeap;

    @BeforeEach
    public void setUp() {
        maxHeap = new MaxHeap<>();
    }

    @Test
    public void testAdd() {
        maxHeap.add(10);
        maxHeap.add(15);
        maxHeap.add(5);
        assertEquals(3, maxHeap.size());
        assertBackingArrayEquals(new Integer[]{null, 15, 10, 5, null, null, null, null, null, null, null, null, null}, maxHeap.getBackingArray());
    }

    @Test
    public void testRemove() {
        maxHeap.add(10);
        maxHeap.add(15);
        maxHeap.add(5);
        int max = maxHeap.remove();
        assertEquals(15, max);
        assertEquals(2, maxHeap.size());
        assertBackingArrayEquals(new Integer[]{null, 10, 5, null, null, null, null, null, null, null, null, null, null}, maxHeap.getBackingArray());
    }

    @Test
    public void testAddNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            maxHeap.add(null);
        });
    }

    @Test
    public void testRemoveEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            maxHeap.remove();
        });
    }

    @Test
    public void testResize() {
        for (int i = 0; i < 14; i++) {
            maxHeap.add(i);
        }
        assertEquals(14, maxHeap.size());
        assertEquals(14, countNonNullElements(maxHeap.getBackingArray()));
    }

    private void assertBackingArrayEquals(Integer[] expected, Comparable[] actual) {
        assertEquals(expected.length, actual.length, "Array lengths differ.");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i], "Arrays differ at index " + i);
        }
    }

    private int countNonNullElements(Comparable[] array) {
        int count = 0;
        for (Comparable element : array) {
            if (element != null) {
                count++;
            }
        }
        return count;
    }
}
