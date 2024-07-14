import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

/**
 * Your implementation of various iterative sorting algorithms.
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * NOTE: You should implement bubble sort with the last swap optimization.
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        boolean swapped;
        int n = arr.length;
        do {
            swapped = false;
            int lastSwappedIndex = 0;
            for (int i = 1; i < n; i++) {
                if (comparator.compare(arr[i - 1], arr[i]) > 0) {
                    T temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                    swapped = true;
                    lastSwappedIndex = i;
                }
            }
            n = lastSwappedIndex;
        } while (swapped);
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n^2)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            T temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        for (int i = 1; i < arr.length; i++) {
            T key = arr[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

        /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(n log n)
     * And a best case running time of: O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: You may need to create a helper method that merges two arrays
     * back into the original T[] array. If two data are equal when merging,
     * think about which subarray you should pull from first.
     *
     * You may assume that the passed in array and comparator are both valid
     * and will not be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array to be sorted.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr.length > 1) {
            int mid = arr.length / 2;

            T[] left = (T[]) new Object[mid];
            T[] right = (T[]) new Object[arr.length - mid];

            for (int i = 0; i < mid; i++) {
                left[i] = arr[i];
            }
            for (int i = mid; i < arr.length; i++) {
                right[i - mid] = arr[i];
            }

            mergeSort(left, comparator);
            mergeSort(right, comparator);

            merge(arr, left, right, comparator);
        }
    }

    private static <T> void merge(T[] arr, T[] left, T[] right, Comparator<T> comparator) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(kn)
     * And a best case running time of: O(kn)
     *
     * Feel free to make an initial O(n) passthrough of the array to
     * determine k, the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * You may use an ArrayList or LinkedList if you wish, but it should only
     * be used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with merge sort. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * You may assume that the passed in array is valid and will not be null.
     *
     * @param arr The array to be sorted.
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        final int RADIX = 10;
        List<Integer>[] positiveBuckets = new ArrayList[RADIX];
        List<Integer>[] negativeBuckets = new ArrayList[RADIX];
        for (int i = 0; i < RADIX; i++) {
            positiveBuckets[i] = new ArrayList<>();
            negativeBuckets[i] = new ArrayList<>();
        }

        boolean maxLength = false;
        int tmp = -1, place = 1;

        while (!maxLength) {
            maxLength = true;
            for (Integer i : arr) {
                tmp = i / place;
                int bucketIndex = Math.abs(tmp % RADIX);
                if (i < 0) {
                    negativeBuckets[bucketIndex].add(i);
                } else {
                    positiveBuckets[bucketIndex].add(i);
                }
                if (maxLength && tmp != 0) {
                    maxLength = false;
                }
            }

            int a = 0;
            for (int b = RADIX - 1; b >= 0; b--) {
                for (Integer i : negativeBuckets[b]) {
                    arr[a++] = i;
                }
                negativeBuckets[b].clear();
            }
            for (int b = 0; b < RADIX; b++) {
                for (Integer i : positiveBuckets[b]) {
                    arr[a++] = i;
                }
                positiveBuckets[b].clear();
            }
            if (place > Integer.MAX_VALUE / RADIX) {
                break;
            }
            place *= RADIX;

            if (place <= 0) {
                throw new ArithmeticException("Overflow occurred in place variable.");
            }
        }
    }
}
