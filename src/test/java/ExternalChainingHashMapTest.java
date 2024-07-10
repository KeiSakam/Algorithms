import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExternalChainingHashMapTest {

    private static final int INITIAL_CAPACITY = 13;
    private ExternalChainingHashMap<Integer, String> map;

    @BeforeEach
    public void setUp() {
        map = new ExternalChainingHashMap<>();
    }

    @Test
    public void testPutAndGet() {
        assertNull(map.put(1, "one"));
        assertNull(map.put(2, "two"));
        assertEquals("one", map.put(1, "ONE"));
        assertEquals("ONE", map.getTable()[Math.abs(1 % map.getTable().length)].getValue());
        assertEquals("two", map.getTable()[Math.abs(2 % map.getTable().length)].getValue());
    }

    @Test
    public void testRemove() {
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        assertEquals("two", map.remove(2));
        assertEquals(2, map.size());
        try {
            map.remove(2);
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // expected
        }
    }

    @Test
    public void testResize() {
        for (int i = 1; i <= 10; i++) {
            map.put(i, "value" + i);
        }
        assertTrue(map.getTable().length > ExternalChainingHashMap.INITIAL_CAPACITY);
    }
}
