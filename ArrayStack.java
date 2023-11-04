import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayStack.
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
public class ArrayStack<T> {

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
    private int size;

    /**
     * Constructs a new ArrayStack.
     */
    public ArrayStack() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Adds the data to the top of the stack.
     * <p>
     * If sufficient space is not available in the backing array, resize it to
     * double the current length.
     * <p>
     * Must be amortized O(1).
     *
     * @param data the data to add to the top of the stack
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("This method does not "
                    + "allow null values to be added to this data structure. ");
        }
        if (size >= backingArray.length) {
            T[] tempArr = (T[]) new Object[2 * backingArray.length];
            for (int i = 0; i < size; i++) {
                tempArr[i] = backingArray[i];
            }
            backingArray = tempArr;
        }
        backingArray[size] = data;
        size++;
    }

    /**
     * Removes and returns the data from the top of the stack.
     * <p>
     * Do not shrink the backing array.
     * <p>
     * Replace any spots that you pop from with null.
     * <p>
     * Must be O(1).
     *
     * @return the data formerly located at the top of the stack
     * @throws java.util.NoSuchElementException if the stack is empty
     */
    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("The data structure is "
                    + "empty, so no elements can be removed.");
        }
        T deletedElement = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return deletedElement;
    }


    /**
     * Returns the data from the top of the stack without removing it.
     * <p>
     * Must be O(1).
     *
     * @return the data from the top of the stack
     * @throws java.util.NoSuchElementException if the stack is empty
     */
    public T peek() {
        if (size == 0) {
            throw new NoSuchElementException("The data structure is "
                    + "empty, so no elements can be removed.");
        }
        return backingArray[size - 1];
    }

    /**
     * Returns the backing array of the stack.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the stack
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the stack.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the stack
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
