import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;  // Initialize size to 0
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        if (size + 1 == backingArray.length) {
            resize();
        }

        backingArray[++size] = data;
        upheap(size);
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }

        T min = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size--] = null;
        downheap(1);

        return min;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Resize the backing array by doubling its capacity.
     */
    private void resize() {
        T[] newArray = (T[]) new Comparable[backingArray.length * 2];
        for (int i = 1; i <= size; i++) {
            newArray[i] = backingArray[i];
        }
        backingArray = newArray;
    }

    /**
     * Upheap (bubble up) operation to maintain the heap property after addition.
     */
    private void upheap(int index) {
        while (index > 1) {
            int parentIndex = index / 2;
            if (backingArray[index].compareTo(backingArray[parentIndex]) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    /**
     * Downheap (bubble down) operation to maintain the heap property after removal.
     */
    private void downheap(int index) {
        while (index * 2 <= size) {
            int leftChildIndex = index * 2;
            int rightChildIndex = leftChildIndex + 1;
            int smallestChildIndex = leftChildIndex;

            if (rightChildIndex <= size && backingArray[rightChildIndex].compareTo(backingArray[leftChildIndex]) < 0) {
                smallestChildIndex = rightChildIndex;
            }

            if (backingArray[index].compareTo(backingArray[smallestChildIndex]) > 0) {
                swap(index, smallestChildIndex);
                index = smallestChildIndex;
            } else {
                break;
            }
        }
    }

    /**
     * Swap the elements at two indices in the backing array.
     */
    private void swap(int index1, int index2) {
        T temp = backingArray[index1];
        backingArray[index1] = backingArray[index2];
        backingArray[index2] = temp;
    }
}