import java.util.NoSuchElementException;

/**
 * Your implementation of a MaxHeap.
 */
public class MaxHeap<T extends Comparable<? super T>> {

    /*
     * The initial capacity of the MaxHeap when created with the default
     * constructor.
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
     * This is the constructor that constructs a new MaxHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MaxHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
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
        heapifyUp(size);
    }

    /**
     * Removes and returns the max item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * You may assume that the heap is not empty.
     *
     * @return The data that was removed.
     */
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty.");
        }

        T removedData = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;

        heapifyDown(1);

        return removedData;
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
     * Heapify up (bubble up) operation to maintain the heap property after addition.
     */
    private void heapifyUp(int index) {
        while (index > 1) {
            int parentIndex = index / 2;
            if (backingArray[index].compareTo(backingArray[parentIndex]) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    /**
     * Heapify down (bubble down) operation to maintain the heap property after removal.
     */
    private void heapifyDown(int index) {
        while (index * 2 <= size) {
            int largestChildIndex = index * 2;

            if (largestChildIndex < size && backingArray[largestChildIndex + 1].compareTo(backingArray[largestChildIndex]) > 0) {
                largestChildIndex++;
            }

            if (backingArray[index].compareTo(backingArray[largestChildIndex]) >= 0) {
                break;
            }

            swap(index, largestChildIndex);
            index = largestChildIndex;
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
