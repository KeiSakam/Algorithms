import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

public class MinHeapTest {
    private MinHeap<Integer> minHeap;

    @BeforeEach
    public void setUp() {
        minHeap = new MinHeap<>();
    }

    @Test
    public void testAdd() {
        minHeap.add(10);
        minHeap.add(15);
        minHeap.add(5);
        assertEquals(3, minHeap.size());
        assertBackingArrayEquals(new Integer[]{null, 5, 15, 10, null, null, null, null, null, null, null, null, null}, minHeap.getBackingArray());
    }

    @Test
    public void testRemove() {
        minHeap.add(10);
        minHeap.add(15);
        minHeap.add(5);
        int min = minHeap.remove();
        assertEquals(5, min);
        assertEquals(2, minHeap.size());
        assertBackingArrayEquals(new Integer[]{null, 10, 15, null, null, null, null, null, null, null, null, null, null}, minHeap.getBackingArray());
    }

    @Test
    public void testAddNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            minHeap.add(null);
        });
    }

    @Test
    public void testRemoveEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            minHeap.remove();
        });
    }

    @Test
    public void testResize() {
        for (int i = 0; i < 14; i++) {
            minHeap.add(i);
        }
        assertEquals(14, minHeap.size());
        assertEquals(14, countNonNullElements(minHeap.getBackingArray()));
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
