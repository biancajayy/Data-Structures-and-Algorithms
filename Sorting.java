import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Bianca Jayaraman
 * @version 1.0
 * @userid bjayaraman9
 * @GTID 903754012
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class Sorting {

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n^2)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("This method does not accept "
                    + "null data.");
        }
        for (int i = arr.length - 1; i > 0; i--) {
            int maxIndex = i;
            for (int j = 0; j < i; j++) {
                if (comparator.compare(arr[j], arr[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }
            swap(maxIndex, i, arr);
        }
    }
    /**
     * Helper method for swapping two elements in an array
     * @param <T> data type to sort
     * @param element1 first element to be swapped
     * @param element2 second element to be swapped
     * @param swapArray the array that is having swaps made in it
     */
    private static <T> void swap(int element1, int element2, T[] swapArray) {
        T ele1PlaceHolder = swapArray[element1];
        swapArray[element1] = swapArray[element2];
        swapArray[element2] = ele1PlaceHolder;
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("This method does not accept "
                    + "null data.");
        }
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                swap(j, j - 1, arr);
                j--;
            }
        }
    }

    /**
     * Implement bubble sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for bubble sort. You
     * MUST implement bubble sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("This method does not accept "
                    + "null data.");
        }
        int stopIndex = arr.length - 1;
        while (stopIndex != 0) {
            int i = 0;
            int lastSwapped = 0;
            while (i < stopIndex) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    lastSwapped = i;
                    swap(i, i + 1, arr);
                }
                i++;
            }
            stopIndex = lastSwapped;
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
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("This method does not accept "
                    + "null data.");
        }
        int initialLength = arr.length;
        int midIndex = initialLength / 2;
        if (initialLength > 1) {
            T[] leftArray = (T[]) new Object[midIndex];
            T[] rightArray = (T[]) new Object[initialLength - leftArray.length];

            for (int i = 0; i < leftArray.length; i++) {
                leftArray[i] = arr[i];
            }
            for (int j = 0; j < rightArray.length; j++) {
                rightArray[j] = arr[j + leftArray.length];
            }
            mergeSort(leftArray, comparator);
            mergeSort(rightArray, comparator);
            merge(arr, leftArray, rightArray, comparator);
        }
    }
    /**
     * Helper method for merge sort
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param leftArr    the left subarray that will be sorted after the method runs
     * @param rightArr   the right subarray that will be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    private static <T> void merge(T[] arr, T[] leftArr, T[] rightArr, Comparator<T> comparator) {
        int i = 0;
        int j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            if (comparator.compare(leftArr[i], rightArr[j]) <= 0) {
                arr[i + j] = leftArr[i];
                i++;
            } else {
                arr[i + j] = rightArr[j];
                j++;
            }
        }
        while (i < leftArr.length) {
            arr[i + j] = leftArr[i];
            i++;
        }
        while (j < rightArr.length) {
            arr[i + j] = rightArr[j];
            j++;
        }
    }


    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("This method does not accept "
                    + "null data.");
        }
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < 19; i++) {
            buckets[i] = new LinkedList<>();
        }
        int iterations = lsdDigitCounter(arr);
        int initialLength = arr.length;
        int dividingNum = 1;
        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < initialLength; j++) {
                int bucketNum = (arr[j] / dividingNum) % 10;
//                if (buckets[bucketNum + 9] == null) {
//                    buckets[bucketNum + 9] = new LinkedList<>();
//                }
                buckets[bucketNum + 9].add(arr[j]);
            }
            int bucketArrayIndex = 0;
            for (LinkedList<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    arr[bucketArrayIndex++] = bucket.remove();
                }
                bucket.clear();
            }
            dividingNum *= 10;
        }
    }
    /**
     * Helper method for merge sort
     * @param arr        the array that must be sorted after the method runs
     * @return the largest number of digits in a number in the array
     */
    private static int lsdDigitCounter(int[] arr) {
        int count = 0;
        int currentLargest = Math.abs(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (Math.abs(arr[i]) > currentLargest) {
                currentLargest = Math.abs(arr[i]);
            }
        }
        while (currentLargest > 0) {
            currentLargest = currentLargest / 10;
            count++;
        }
        return count;
    }

    /**
     * Implement heap sort.
     *
     * It should be:
     * out-of-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Use java.util.PriorityQueue as the heap. Note that in this
     * PriorityQueue implementation, elements are removed from smallest
     * element to largest element.
     *
     * Initialize the PriorityQueue using its build heap constructor (look at
     * the different constructors of java.util.PriorityQueue).
     *
     * Return an int array with a capacity equal to the size of the list. The
     * returned array should have the elements in the list in sorted order.
     *
     * @param data the data to sort
     * @return the array with length equal to the size of the input list that
     * holds the elements from the list is sorted order
     * @throws java.lang.IllegalArgumentException if the data is null
     */
    public static int[] heapSort(List<Integer> data) {
        if (data == null) {
            throw new IllegalArgumentException("This method does not accept "
                    + "null data.");
        }
        int[] sortedArray = new int[data.size()];
        PriorityQueue<Integer> heap = new PriorityQueue<>(data);
        for (int i = 0; i < data.size(); i++) {
            sortedArray[i] = heap.remove();
        }
        return sortedArray;
    }
}