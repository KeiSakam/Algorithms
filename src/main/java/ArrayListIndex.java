import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayListIndex.
 */
public class ArrayListIndex<T> {

    /*
     * The initial capacity of the ArrayListIndex.
     *
     * DO NOT MODIFY THIS VARIABLE.
     */
    public static final int INITIAL_CAPACITY = 9;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new ArrayListIndex.
     * 
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast an Object[] to a T[] to get the generic typing.
     */
    @SuppressWarnings("unchecked")
    public ArrayListIndex() {
        // DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Adds the data at the specified index.
     *
     * If sufficient space is not available in the backing array, resize it to
     * double the current length. When resizing, copy elements to the
     * beginning of the new array.
     *
     * Method should run in amortized O(1) time.
     *
     * @param index the index to add the data at
     * @param data the data to add at the specified index
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.lang.IndexOutOfBoundsException if index is negative or 
     *                                            index > size
     */
    @SuppressWarnings("unchecked")
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (size == backingArray.length) {
            T[] newArray = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < size; i++) {
                newArray[i] = backingArray[i];
            }
            backingArray = newArray;
        }
        for (int i = size; i > index; i--) {
            backingArray[i] = backingArray[i - 1];
        }
        backingArray[index] = data;
        size++;
    }

    /**
     * Removes the data at the specified index.
     *
     * Method should run in O(n) time.
     *
     * @param index the index to remove the data from
     * @return the data formerly located at the specified index
     * @throws java.util.NoSuchElementException if the list is empty
     * @throws java.lang.IndexOutOfBoundsException if index is negative or 
     *                                            index >= size
     */
    public T removeAtIndex(int index) {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        T data = backingArray[index];
        for (int i = index; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        backingArray[size - 1] = null;
        size--;
        return data;
    }

    /**
     * Returns the backing array of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
