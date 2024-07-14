import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SortingTest {
    private Integer[] unsortedArray;
    private Integer[] sortedArray;

    @BeforeEach
    public void setUp() {
        unsortedArray = new Integer[]{4, 3, 1, 5, 2, 6, 7};
        sortedArray = new Integer[]{1, 2, 3, 4, 5, 6, 7};
    }

    @Test
    public void testBubbleSort() {
        Integer[] arrayToSort = Arrays.copyOf(unsortedArray, unsortedArray.length);
        Sorting.bubbleSort(arrayToSort, Comparator.naturalOrder());
        assertArrayEquals(sortedArray, arrayToSort);
    }

    @Test
    public void testSelectionSort() {
        Integer[] arrayToSort = Arrays.copyOf(unsortedArray, unsortedArray.length);
        Sorting.selectionSort(arrayToSort, Comparator.naturalOrder());
        assertArrayEquals(sortedArray, arrayToSort);
    }

    @Test
    public void testInsertionSort() {
        Integer[] arrayToSort = Arrays.copyOf(unsortedArray, unsortedArray.length);
        Sorting.insertionSort(arrayToSort, Comparator.naturalOrder());
        assertArrayEquals(sortedArray, arrayToSort);
    }
    
    @Test
    public void testMergeSort() {
        Integer[] arrayToSort = Arrays.copyOf(unsortedArray, unsortedArray.length);
        Sorting.mergeSort(arrayToSort, Comparator.naturalOrder());
        assertArrayEquals(sortedArray, arrayToSort);
    }

    @Test
    public void testBubbleSortWithDuplicates() {
        Integer[] arrayWithDuplicates = {4, 3, 3, 5, 2, 6, 7};
        Integer[] sortedWithDuplicates = {2, 3, 3, 4, 5, 6, 7};
        Sorting.bubbleSort(arrayWithDuplicates, Comparator.naturalOrder());
        assertArrayEquals(sortedWithDuplicates, arrayWithDuplicates);
    }

    @Test
    public void testSelectionSortWithDuplicates() {
        Integer[] arrayWithDuplicates = {4, 3, 3, 5, 2, 6, 7};
        Integer[] sortedWithDuplicates = {2, 3, 3, 4, 5, 6, 7};
        Sorting.selectionSort(arrayWithDuplicates, Comparator.naturalOrder());
        assertArrayEquals(sortedWithDuplicates, arrayWithDuplicates);
    }

    @Test
    public void testInsertionSortWithDuplicates() {
        Integer[] arrayWithDuplicates = {4, 3, 3, 5, 2, 6, 7};
        Integer[] sortedWithDuplicates = {2, 3, 3, 4, 5, 6, 7};
        Sorting.insertionSort(arrayWithDuplicates, Comparator.naturalOrder());
        assertArrayEquals(sortedWithDuplicates, arrayWithDuplicates);
    }
    
    @Test
    public void testMergeSortWithDuplicates() {
        Integer[] arrayWithDuplicates = {4, 3, 3, 5, 2, 6, 7};
        Integer[] sortedWithDuplicates = {2, 3, 3, 4, 5, 6, 7};
        Sorting.mergeSort(arrayWithDuplicates, Comparator.naturalOrder());
        assertArrayEquals(sortedWithDuplicates, arrayWithDuplicates);
    }
    
    @Test
    public void testBubbleSortStability() {
        Person[] people = {
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Charlie", 25),
            new Person("David", 40)
        };
        Person[] sortedPeople = {
            new Person("Bob", 25),
            new Person("Charlie", 25),
            new Person("Alice", 30),
            new Person("David", 40)
        };
        Sorting.bubbleSort(people, Comparator.comparingInt(Person::getAge));
        assertArrayEquals(sortedPeople, people);
    }

    @Test
    public void testInsertionSortStability() {
        Person[] people = {
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Charlie", 25),
            new Person("David", 40)
        };
        Person[] sortedPeople = {
            new Person("Bob", 25),
            new Person("Charlie", 25),
            new Person("Alice", 30),
            new Person("David", 40)
        };
        Sorting.insertionSort(people, Comparator.comparingInt(Person::getAge));
        assertArrayEquals(sortedPeople, people);
    }

    @Test
    public void testMergeSortStability() {
        Person[] people = {
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Charlie", 25),
            new Person("David", 40)
        };
        Person[] sortedPeople = {
            new Person("Bob", 25),
            new Person("Charlie", 25),
            new Person("Alice", 30),
            new Person("David", 40)
        };
        Sorting.mergeSort(people, Comparator.comparingInt(Person::getAge));
        assertArrayEquals(sortedPeople, people);
    }

    @Test
    public void testLsdRadixSort() {
        int[] unsortedArray = {1000, 2014, 231, 53, -1, -92, -9403, 634, -207};
        int[] sortedArray = {-9403, -207, -92, -1, 53, 231, 634, 1000, 2014};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Person person = (Person) obj;
            return age == person.age && name.equals(person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
        }
    }
}
