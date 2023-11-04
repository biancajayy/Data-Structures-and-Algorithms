import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayQueue.
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
public class ArrayQueue<T> {

    /*
     * The initial capacity of the ArrayQueue.
     *
     * DO NOT MODIFY THIS VARIABLE.
     */
    public static final int INITIAL_CAPACITY = 9;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int front;
    private int size;

    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Adds the data to the back of the queue.
     *
     * If sufficient space is not available in the backing array, resize it to
     * double the current length. When resizing, copy elements to the
     * beginning of the new array and reset front to 0.
     *
     * Must be amortized O(1).
     *
     * @param data the data to add to the back of the queue
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("This method does not allow "
                    + "null data to be added to the data structure.");
        }
        if (size >= backingArray.length) {
            T[] tempArray = (T[]) new Object[2 * size];
            for (int i = 0; i < size; i++) {
                tempArray[i] = backingArray[(front + i) % size];
            }
            backingArray = tempArray;
            front = 0;
        }
        int back = (front + size) % backingArray.length;
        size++;
        backingArray[back] = data;
    }


    /**
         * Removes and returns the data from the front of the queue.
         *
         * Do not shrink the backing array.
         *
         * Replace any spots that you dequeue from with null.
         *
         * If the queue becomes empty as a result of this call, do not reset
         * front to 0.
         *
         * Must be O(1).
         *
         * @return the data formerly located at the front of the queue
         * @throws java.util.NoSuchElementException if the queue is empty
         */
    @SuppressWarnings("checkstyle:OperatorWrap")
    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("The data structure is "
                + "empty, so no elements can be removed.");
        }
        T deletedElement = backingArray[front];
        backingArray[front] = null;
        front++;
        size--;
        return deletedElement;
    }

    /**
    * Returns the data from the front of the queue without removing it.
    *
    * Must be O(1).
    *
    * @return the data located at the front of the queue
    * @throws java.util.NoSuchElementException if the queue is empty
    */
    public T peek() {
        if (size == 0) {
            throw new NoSuchElementException("The data structure is "
                + "empty, so there are no elements to access.");
        }
        return backingArray[front];
    }

    /**
    * Returns the backing array of the queue.
    *
    * For grading purposes only. You shouldn't need to use this method since
    * you have direct access to the variable.
    *
    * @return the backing array of the queue
    */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
         * Returns the size of the queue.
         *
         * For grading purposes only. You shouldn't need to use this method since
         * you have direct access to the variable.
         *
         * @return the size of the queue
         */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
