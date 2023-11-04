import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Bianca Jayaraman
 * @userid bjayaraman9
 * @GTID 903754012
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it appears in the Collection.
     *
     * @throws IllegalArgumentException if data or any element in data is null
     * @param data the data to add to the tree
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("This data structure"
                    + "does not accept null values.");
        } else {
            for (T datum : data) {
                if (data != null) {
                    add(datum);
                } else {
                    throw new IllegalArgumentException("This data structure"
                            + "does not accept null values.");
                }
            }
        }
    }

    /**
     * Adds the data to the AVL. Start by adding it as a leaf like in a regular
     * BST and then rotate the tree as needed.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors going up the tree,
     * rebalancing if necessary.
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("This method does not accept"
                    + " null data.");
        }
        root = rAdd(root, data);
    }

    /**
     * Helper method for the add method. Comparisons are made to
     * determine where data should be added in the tree.
     *
     * @param curr current node being compared
     * @param data data that is being compared to the current node
     * @return balanced root
     */
    private AVLNode<T> rAdd(AVLNode<T> curr, T data) {
        if (curr == null) {
            size++;
            return new AVLNode<T>(data);
        } else if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(rAdd(curr.getLeft(), data));
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(rAdd(curr.getRight(), data));
        }
        updateHeightBF(curr);
        return balance(curr);
    }

    /**
     * Helper method for the add/remove/rotation methods. Comparisons are made
     * to determine updated Balance factors and heights.
     *
     * @param curr current node being compared
     */
    private void updateHeightBF(AVLNode<T> curr) {
        int leftChildHeight = -1;
        int rightChildHeight = -1;

        if (curr.getLeft() != null) {
            leftChildHeight = curr.getLeft().getHeight();
        }
        if (curr.getRight() != null) {
            rightChildHeight = curr.getRight().getHeight();
        }
        curr.setBalanceFactor(leftChildHeight - rightChildHeight);
        curr.setHeight(Math.max(leftChildHeight, rightChildHeight) +  1);
    }
    /**
     * Helper method for the add/remove methods. Comparisons are made
     * to determine what kind of rotation needs to be made
     *
     * @param curr current node being compared
     * @return new root post balancing
     */
    private AVLNode<T> balance(AVLNode<T> curr) {
        if (curr.getBalanceFactor() == 2) {
            if (curr.getLeft().getBalanceFactor() >= 0) {
                curr = rightRotation(curr);
            } else {
                curr.setLeft(leftRotation(curr.getLeft()));
                curr = rightRotation(curr);
            }
        } else if (curr.getBalanceFactor() == -2) {
            if (curr.getRight().getBalanceFactor() <= 0) {
                curr = leftRotation(curr);
            } else {
                curr.setRight(rightRotation(curr.getRight()));
                curr = leftRotation(curr);
            }
        }
        return curr;
    }
    /**
     * Helper method for the balance method. A right rotation is made.
     *
     * @param curr current node being rotated
     * @return new root post balancing
     */
    private AVLNode<T> rightRotation(AVLNode<T> curr) {
        AVLNode<T> bNode = curr.getLeft();
        curr.setLeft(bNode.getRight());
        bNode.setRight(curr);
        updateHeightBF(curr);
        updateHeightBF(bNode);
        return bNode;
    }
    /**
     * Helper method for the balance method. A left rotation is made.
     *
     * @param curr current node being rotated
     * @return new root post balancing
     */
    private AVLNode<T> leftRotation(AVLNode<T> curr) {
        AVLNode<T> bNode = curr.getRight();
        curr.setRight(bNode.getLeft());
        bNode.setLeft(curr);
        updateHeightBF(curr);
        updateHeightBF(bNode);
        return bNode;
    }

    /**
     * Removes the data from the tree. There are 3 cases to consider:
     *
     * 1: the data is a leaf. In this case, simply remove it.
     * 2: the data has one child. In this case, simply replace it with its
     * child.
     * 3: the data has 2 children. Use the predecessor to replace the data,
     * not the successor. As a reminder, rotations can occur after removing
     * the predecessor node.
     *
     * Remember to recalculate heights going up the tree, rebalancing if
     * necessary.
     *
     * @throws IllegalArgumentException if the data is null
     * @throws NoSuchElementException if the data is not found
     * @param data the data to remove from the tree.
     * @return the data removed from the tree. Do not return the same data
     * that was passed in.  Return the data that was stored in the tree.
     */

    //HAVE TO CHANGE FROM BST
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("This method does not "
                    + "accept null data.");
        }
        AVLNode<T> deletedData = new AVLNode<>(null);
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
     * @return pointer reinforcement, balanced root
     */
    private AVLNode<T> rRemove(AVLNode<T> curr, T data, AVLNode<T> deletedData) {
        if (curr == null) {
            throw new NoSuchElementException("This element does not exist "
                    + "within this tree");
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
                AVLNode<T> predecessor = new AVLNode<>(null);
                curr.setLeft(predecessorHelper(curr.getLeft(), predecessor));
                curr.setData(predecessor.getData());
            }
        }
        updateHeightBF(curr);
        return balance(curr);
    }
    /**
     * Another helper method for the remove method. Finds the predecessor of
     * data and is called when the 2 child case is reached.
     *
     * @param curr current node being compared
     * @param predecessor predecessor node
     * @return re-establishes connection points
     */
    private AVLNode<T> predecessorHelper(AVLNode<T> curr, AVLNode<T> predecessor) {
        if (curr.getRight() == null) {
            predecessor.setData(curr.getData());
            return curr.getLeft();
        } else {
            curr.setRight(predecessorHelper(curr.getRight(), predecessor));
            return curr;
        }
    }

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws NoSuchElementException if the data is not found
     * @param data the data to search for in the tree.
     * @return the data in the tree equal to the parameter. Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
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
    private T rGet(AVLNode<T> curr, T data) {
        if (curr == null) {
            throw new NoSuchElementException("This element does not exist "
                    + "within this tree.");
        }

        if (curr.getData().equals(data)) {
            return curr.getData();
        } else if (data.compareTo(curr.getData()) > 0) {
            return rGet(curr.getRight(), data);
        } else if (data.compareTo(curr.getData()) < 0) {
            return rGet(curr.getLeft(), data);
        }
        return null;
    }

    /**
     * Returns whether or not data equivalent to the given parameter is
     * contained within the tree. The same type of equality should be used as
     * in the get method.
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to search for in the tree.
     * @return whether or not the parameter is contained within the tree.
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
    private boolean rContains(AVLNode<T> curr, T data) {
        if (curr == null) {
            return false;
        }
        if (curr.getData().equals(data)) {
            return true;
        } else if (data.compareTo(curr.getData()) > 0) {
            return rContains(curr.getRight(), data);
        } else if (data.compareTo(curr.getData()) < 0) {
            return rContains(curr.getLeft(), data);
        }
        return false;
    }

    /**
     * Finds and retrieves the median data of the passed in AVL. 
     * 
     * This method will not need to traverse the entire tree to
     * function properly, so you should only traverse enough branches of the tree
     * necessary to find the median data and only do so once. Failure to do so will
     * result in an efficiency penalty.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *              50
     *            /    \
     *         25      75
     *        /  \     / \
     *      13   37  70  80
     *    /  \    \      \
     *   12  15    40    85
     * 
     * findMedian() should return 40
     *
     * @throws NoSuchElementException if the tree is empty or contains an even number of data
     * @return the median data of the AVL
     */
    public T findMedian() {
        if (size == 0 || size % 2 == 0) {
            throw new NoSuchElementException("The median can not be found in "
                    + "this size tree.");
        }
        if (root.getBalanceFactor() == 0) {
            return root.getData();
        } else {
            AVLNode<T> median = new AVLNode<>(null);
            AVLNode<Integer> count = new AVLNode<>(0);
            rInOrder(root, median, count);
            return median.getData();
        }
    }

    /**
     * Helper method for the median method. Recursively computes an inorder
     * traversal, stopping when the median has been reached.
     *
     * @param curr current node being compared
     * @param median dummy node that stores median when found
     * @param count is the dummy node used to keep track of
     * the in-order traversal
     */
    private void rInOrder(AVLNode<T> curr, AVLNode<T> median, AVLNode<Integer> count) {
        if (curr == null || count.getData() == size / 2 + 1) {
            return;
        }
        rInOrder(curr.getLeft(), median, count);
        if (median.getData() == null) {
            count.setData(count.getData() + 1);
            if (count.getData() == size / 2 + 1) {
                median.setData(curr.getData());
                return;
            }
            rInOrder(curr.getRight(), median, count);
        }
    }
    /**
     * Clears the tree.
     */
    public void clear() {
        size = 0;
        root = null;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * Since this is an AVL, this method does not need to traverse the tree
     * and should be O(1)
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (size == 0) {
            return -1;
        }
        return (Math.max(root.getLeft().getHeight(), root.getRight().getHeight()) + 1);
    }

    /**
     * Returns the size of the AVL tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return number of items in the AVL tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    /**
     * Returns the root of the AVL tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the AVL tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
