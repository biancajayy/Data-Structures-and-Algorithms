import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Your implementation of a BST.
 *
 * Bianca Jayaraman
 * bjayaraman9
 * 903754012
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        for (T datum : data) {
            if (data != null) {
                add(datum);
            } else {
                throw new IllegalArgumentException("This data structure"
                        + "does not accept null values.");
            }
        }
    }

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("This data structure"
                    + "does not accept null values.");
        }
        root = rAdd(root, data);
    }
    /**
     * Helper method for the add method. Comparisons are made to
     * determine where data should be added in the tree.
     *
     * @param curr current node being compared
     * @param data data that is being compared to the current node
     * @return new root
     */
    private BSTNode rAdd(BSTNode curr, T data) {
        if (curr == null) {
            size++;
            return new BSTNode<T>(data);
        } else if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(rAdd(curr.getLeft(), data));
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(rAdd(curr.getRight(), data));
        }
        return curr;
    }


    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("This method does not "
                    + "accept null data.");
        }
        BSTNode<T> deletedData = new BSTNode<>(null);
        root = rRemove(root, data, deletedData);
        return deletedData.getData();
    }
    /**
     * Helper method for the remove method. Comparisons are made to search
     * for data. This considers the 0, 1, and 2 child cases.
     *
     * @param curr current node being compared
     * @param data data that is being compared to the current node
     * @param deletedData data that is deleted
     * @return pointer reinforcement, re-establishes connection points
     */
    private BSTNode<T> rRemove(BSTNode curr, T data, BSTNode deletedData) {
        if (curr == null) {
            return null;
        }
        if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(rRemove(curr.getLeft(), data, deletedData));
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(rRemove(curr.getRight(), data, deletedData));
        } else {
            size--;
            deletedData.setData(curr.getData());
            if (curr.getLeft() == null) {
                return curr.getRight();
            } else if (curr.getRight() == null) {
                return curr.getLeft();
            } else {
                BSTNode<T> successor = new BSTNode<>(null);
                curr.setRight(successorHelper(curr.getRight(), successor));
                curr.setData(successor.getData());
            }
        }
        return curr;
    }
    /**
     * Another helper method for the remove method. Finds the successor of data
     * and is called when the 2 child case is reached.
     *
     * @param curr current node being compared
     * @param successor successor node
     * @return re-establishes connection points
     */
    private BSTNode<T> successorHelper(BSTNode<T> curr, BSTNode<T> successor) {
        if (curr.getLeft() == null) {
            successor.setData(curr.getData());
            return curr.getRight();
        } else {
            curr.setLeft(successorHelper(curr.getLeft(), successor));
            return curr;
        }
    }
    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("This method does not "
                    + "accept null data.");
        }
        return rGet(root, data);
    }
    /**
     * Helper method for the get method. Comparisons are made to search
     * for data.
     *
     * @param curr current node being compared
     * @param data data that is being compared to the current node
     * @return if data is not reached, returns null
     */
    private T rGet(BSTNode curr, T data) {
        if (curr == null) {
            throw new NoSuchElementException("This element does not exist "
                    + "within this BST.");
        }

        if (curr.getData().equals(data)) {
            return (T) curr.getData();
        } else if (data.compareTo((T) curr.getData()) > 0) {
            return rGet(curr.getRight(), data);
        } else if (data.compareTo((T) curr.getData()) < 0) {
            return rGet(curr.getLeft(), data);
        }
        return null;
    }
    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("This method does not "
                    + "accept null data.");
        }
        return rContains(root, data);
    }
    /**
     * Helper method for the contains method. Comparisons are made to search
     * for data.
     *
     * @param curr current node being compared
     * @param data data that is being compared to the current node
     * @return if data is not contained, reaches false
     */
    private boolean rContains(BSTNode curr, T data) {
        if (curr == null) {
            return false;
        }
        if (curr.getData().equals(data)) {
            return true;
        } else if (data.compareTo((T) curr.getData()) > 0) {
            return rContains(curr.getRight(), data);
        } else if (data.compareTo((T) curr.getData()) < 0) {
            return rContains(curr.getLeft(), data);
        }
        return false;
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     * pointer reinforcement?? (ask)
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        ArrayList<T> list = new ArrayList<>();
        rPreorder(root, list);
        return list;
    }
    /**
     * Helper method for the preorder method. Recursively adds data
     * to the list
     *
     * @param curr current node being compared
     * @param list list where data is stored in proper ordering
     */
    private void rPreorder(BSTNode curr, List<T> list) {
        if (curr == null) {
            return;
        }
        list.add((T) curr.getData());
        rPreorder(curr.getLeft(), list);
        rPreorder(curr.getRight(), list);
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        ArrayList<T> list = new ArrayList<>();
        rInOrder(root, list);
        return list;
    }
    /**
     * Helper method for the inorder method. Recursively adds data
     * to the list
     *
     * @param curr current node being compared
     * @param list list where data is stored in proper ordering
     */
    private void rInOrder(BSTNode curr, List<T> list) {
        if (curr == null) {
            return;
        }
        rInOrder(curr.getLeft(), list);
        list.add((T) curr.getData());
        rInOrder(curr.getRight(), list);
    }


    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        ArrayList<T> list = new ArrayList<>();
        rPostOrder(root, list);
        return list;
    }
    /**
     * Helper method for the postorder method. Recursively adds data
     * to the list
     *
     * @param curr current node being compared
     * @param list list where data is stored in proper ordering
     */
    private void rPostOrder(BSTNode curr, List<T> list) {
        if (curr == null) {
            return;
        }
        rPostOrder(curr.getLeft(), list);
        rPostOrder(curr.getRight(), list);
        list.add((T) curr.getData());
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     * *** WAS it okay to import Java.util.queue?
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        Queue<BSTNode<T>> queue = new LinkedList<>();
        List<T> list = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BSTNode<T> curr = queue.remove();
            if (curr != null) {
                queue.add(curr.getLeft());
                queue.add(curr.getRight());
                list.add(curr.getData());
            }
        }
        return list;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return rHeight(root);
    }
    /**
     * Helper method for the Height method. Recursively traverses tree to
     * compare left and right heights.
     *
     * @param curr current node being compared
     * @return height value of the current node
     */
    private int rHeight(BSTNode curr) {
        if ((curr == null)) {
            return -1;
        } else {
            int left = rHeight(curr.getLeft());
            int right = rHeight(curr.getRight());
            return Math.max(left, right) + 1;
        }
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Removes all elements strictly greater than the passed in data.
     *
     * This must be done recursively.
     *
     * In most cases, this method will not need to traverse the entire tree to
     * function properly, so you should only traverse the branches of the tree
     * necessary to get the data and only do so once. Failure to do so will
     * result in an efficiency penalty.
     *
     * EXAMPLE: Given the BST below composed of Integers:
     *
     *                50
     *              /    \
     *            25      75
     *           /  \
     *          12   37
     *         /  \    \
     *        10  15    40
     *           /
     *          13
     *
     * pruneGreaterThan(27) should remove 37, 40, 50, 75. Below is the resulting BST
     *             25
     *            /
     *          12
     *         /  \
     *        10  15
     *           /
     *          13
     *
     * Should have a running time of O(n) for a degenerate tree and O(log(n)) for a balanced tree.
     *
     * @throws java.lang.IllegalArgumentException if data is null
     * @param data the threshold data. Elements greater than data should be removed
     * @param tree the root of the tree to prune nodes from
     * @param <T> the generic typing of the data in the BST
     * @return the root of the tree with all elements greater than data removed
     */
    public static <T extends Comparable<? super T>> BSTNode<T> pruneGreaterThan(BSTNode<T> tree, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data is not accepted "
                    + "in this method");
        }
        return rPrune(tree, data);
        
    }
    /**
     * Helper method for the prune method. Recursively alters tree and
     * makes comparisons between the current data and input data.
     *
     * @param <T> type of data entered
     * @param curr current node being compared
     * @param data all data greater or equal to this value will be removed
     * @return pointer reinforcement, re-establishes connections in tree
     */
    private static <T extends Comparable<? super T>> BSTNode<T> rPrune(BSTNode<T> curr, T data) {
        //think setting each possible as equal? then returning at base case hitting null?
        if (curr == null) {
            return null;
        } else if (curr.getData().compareTo(data) > 0) {
            return rPrune(curr.getLeft(), data);
        } else {
            curr.setRight(rPrune(curr.getRight(), data));
        }
        return curr;
    }



    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
