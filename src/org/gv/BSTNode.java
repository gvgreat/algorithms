package org.gv;

/**
 * @author GV
 *
 */

public final class BSTNode<T> {

	private Comparable<T> data;
	private BSTNode<T> left, right;

	public BSTNode() {
		this(null);
	}
	
	public BSTNode(Comparable<T> key) {
		this.data = key;
	}

	// if key not found in BST then it is added. If key already exists then that node's value
	// is updated.
	public void put(Comparable<T> val) {
		if(this.data == null) {
			data = val;
			return;
		}
		if (val.compareTo((T) data) <= 0) {
			if (left != null) {
				left.put(val);
			} else {
				left = new BSTNode<T>(val);
			}
		} else if (val.compareTo((T) data) > 0) {
			if (right != null) {
				right.put(val);
			} else {
				right = new BSTNode<T>(val);
			}
		} 
	}

	// find Node with given key and return it's value
	public Object search(Comparable<T> key) {
		int comparison = data.compareTo((T) key);
		if (comparison < 0) {
			return right == null ? null : right.search(key);
		} else if (comparison > 0) {
			return left == null ? null : left.search(key);
		}
		return data;
	}

	public int maxDepth(BSTNode root) {
		if (root == null)
			return 0;

		int leftDepth = maxDepth(root.left);
		int rightDepth = maxDepth(root.right);

		int bigger = Math.max(leftDepth, rightDepth);

		return bigger + 1;
	}

	/**
	 * @return the data
	 */
	public Comparable<T> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Comparable<T> data) {
		this.data = data;
	}

	/**
	 * @return the left
	 */
	public BSTNode<T> getLeft() {
		return left;
	}

	/**
	 * @param left
	 *            the left to set
	 */
	public void setLeft(BSTNode<T> left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public BSTNode<T> getRight() {
		return right;
	}

	/**
	 * @param right
	 *            the right to set
	 */
	public void setRight(BSTNode<T> right) {
		this.right = right;
	}
	
	public static void preOrder(BSTNode t) {

		if (t != null) {

			System.out.print(t.data);

			preOrder(t.left);

			preOrder(t.right);

		}

	}
}
