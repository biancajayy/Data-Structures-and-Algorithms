import java.util.NoSuchElementException;

/**
 * Your implementation of a non-circular SinglyLinkedList with a tail pointer.
 *
 * @author Bianca Jayaraman
 * @version 1.0
 * @userid bjayaraman9
 * @GTID 903754012
 *
 * (delete)Ask about size & tail assignement & how to make this run
 */
public class SinglyLinkedList<T> {

    // Do not add new instance variables or modify existing ones.
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index to add the new element
     * @param data  the data to add
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is an invalid value " +
                    "to access elements in this data structure.");
        } else if (data == null) {
            throw new IllegalArgumentException("This data structure " +
                    "does not accept null values.");
        }
        if (head == null) {
            head = new SinglyLinkedListNode<>(data);
            tail = head;
            size ++;
        }
        else if (index == 0) {
            SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(data, head);
            head = node;
            size++;
        } else if (index == size) {
            SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(data);
            tail.setNext(node);
            tail = node;
            size++;
        } else {
            SinglyLinkedListNode<T> current = head;
            int count = 0;
            while (count < index - 1) {
                count++;
                current = current.getNext();
            }
            SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(data, current.getNext());
            current.setNext(node);
            size++;
        }
    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("This data structure " +
                    "does not accept null values.");
        }
        if (head == null) {
            head = new SinglyLinkedListNode<>(data);
            tail = head;
        } else {
            SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(data, head);
            head = node;
        }
        size++;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("This data structure " +
                    "does not accept null values.");
        } if (head == null) {
            head = new SinglyLinkedListNode<>(data);
            tail = head;
        } else {
            SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(data);
            tail.setNext(node);
            tail = tail.getNext();
        }
        size++;
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * Must be O(1) for indices 0 and O(n) for all other
     * cases.
     *
     * @param index the index of the element to remove
     * @return the data that was removed
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is an invalid value " +
                    "to access elements in this data structure.");
        }
        if (index == 0) {
            SinglyLinkedListNode<T> oldNode = head;
            head = head.getNext();
            size--;
            return oldNode.getData();

        } else {
            SinglyLinkedListNode<T> current = head;
            int count = 0;
            while (count < index - 1) {
                current = current.getNext();
                count++;
            }
            if (index == size) {
                SinglyLinkedListNode<T> oldNode = tail;
                tail = current.getNext();
                return oldNode.getData();
            }
            SinglyLinkedListNode<T> oldNode = current.getNext();
            current.setNext(current.getNext().getNext());
            size--;
            return oldNode.getData();
        }
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (head == null) {
            throw new NoSuchElementException("List is empty, first element" +
                    " can not be removed.");
        } else {
            SinglyLinkedListNode<T> oldHead = head;
            head = head.getNext();
            size--;
            return oldHead.getData();
        }
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (head == null) {
            throw new NoSuchElementException("List is empty, last element" +
                    " can not be removed.");
        } else {
            SinglyLinkedListNode<T> current = head;
            while (current.getNext().getNext() != null) {
                current = current.getNext();
            }
            SinglyLinkedListNode<T> oldNode = current.getNext();
            tail = current;
            current.setNext(null);
            size--;
            return oldNode.getData();
        }
    }

    /**
     * Returns the element at the specified index.
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is an invalid value " +
                    "to access elements in this data structure.");
        }
        if (index == 0|| size == 1) {
            return head.getData();
        } else {
            SinglyLinkedListNode<T> current = head;
            int count = 0;
            while (count < index) {
                current = current.getNext();
                count++;
            }
            return current.getData();
        }
    }


    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("This data structure " +
                    "does not accept null values.");
        }
        if (size == 0) {
            throw new NoSuchElementException ("This data does not exist " +
                    "within this data structure.");
        } else {
            SinglyLinkedListNode<T> current = head;
            SinglyLinkedListNode<T> found = null;

            if (head.getData().equals(data)) {
                found = head;
            }
            while (current.getNext() != null) {
                if (current.getNext().getData().equals(data)) {
                    found = current;
                }
                current = current.getNext();
            }
            if (found == null) {
                throw new NoSuchElementException ("This data does not exist " +
                        "within this data structure.");
            } else if (found == head) {
                return removeFromFront();
            } else if (found == tail) {
                return removeFromBack();
            } else {
                T deletedData = found.getNext().getData();
                found.setNext(found.getNext().getNext());
                size --;
                return deletedData;
            }
        }
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * SinglyLinkedListNodes) in the list in the same order
     */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] arr = (T[]) new Object[size];
        SinglyLinkedListNode<T> curr = head;
        int index = 0;
        while(curr != null) {
            arr[index] = curr.getData();
            curr = curr.getNext();
            index++;
        }
        return arr;
    }

    /**
     * Returns the head SinglyLinkedListNode of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the SinglyLinkedListNode at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail SinglyLinkedListNode of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the SinglyLinkedListNode at the tail of the list
     */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
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
        // DO NOT MODIFY!
        return size;
    }
}
