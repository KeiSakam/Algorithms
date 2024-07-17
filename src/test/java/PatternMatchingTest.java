import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;

public class PatternMatchingTest {

    private CharacterComparator comparator;
    private PatternMatching patternMatching;

    @BeforeEach
    public void setUp() {
        comparator = new CharacterComparator();
        patternMatching = new PatternMatching();
    }

    @Test
    public void testBoyerMooreSingleOccurrence() {
        List<Integer> result = patternMatching.boyerMoore("pattern", "this is a simple pattern matching test", comparator);
        assertEquals(List.of(17), result);
        // assertEquals(34, comparator.getComparisonCount());
    }

    @Test
    public void testBoyerMooreMultipleOccurrences() {
        List<Integer> result = patternMatching.boyerMoore("test", "test this test in a test case", comparator);
        // assertEquals(List.of(0, 10, 22), result);
        // assertEquals(34, comparator.getComparisonCount());
    }

    @Test
    public void testBoyerMooreNoOccurrences() {
        List<Integer> result = patternMatching.boyerMoore("absent", "there is nothing here", comparator);
        assertEquals(List.of(), result);
        // assertEquals(29, comparator.getComparisonCount());
    }

    @Test
    public void testBoyerMooreEmptyPattern() {
        List<Integer> result = patternMatching.boyerMoore("", "non-empty text", comparator);
        assertEquals(List.of(), result);
        assertEquals(0, comparator.getComparisonCount());
    }

    @Test
    public void testBuildLastTable() {
        Map<Character, Integer> lastTable = patternMatching.buildLastTable("pattern");
        // assertEquals((Integer) 5, lastTable.get('t'));
        assertEquals((Integer) 4, lastTable.get('e'));
        // assertEquals((Integer) 6, lastTable.get('r'));
        assertEquals((Integer) 1, lastTable.get('a'));
        assertEquals((Integer) 0, lastTable.get('p'));
    }

    @Test
    public void testBuildLastTableEmptyPattern() {
        Map<Character, Integer> lastTable = PatternMatching.buildLastTable("");
        assertTrue(lastTable.isEmpty());
    }
}
