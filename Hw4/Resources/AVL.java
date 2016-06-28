import java.util.Collection;


public class AVL<T extends Comparable<T>> {
	
	private Node<T> root;
	private int size;
	
	/**
	 * Adds a data entry to the AVL tree
	 * 
	 * @param data The data entry to add
	 */
	public void add(T data) {
		
	}
	
	/**
	 * Adds each data entry from the collection to this AVL tree
	 * 
	 * @param c The collection 
	 */
	public void addAll(Collection<? extends T> c) {
		
	}
	
	/**
	 * Removes a data entry from the AVL tree
	 * 
	 * Return null if the value does not exist
	 * 
	 * @param data The data entry to be removed
	 * @return The removed data entry
	 */
	public T remove(T data) {
		return null;
	}
	
	/**
	 * Checks if the AVL tree contains a data entry
	 * 
	 * @param data The data entry to be checked
	 * @return If the data entry is in the AVL tree 
	 */
	public boolean contains(T data) {
		return false;
	}
	
	/**
	 * Calculates the current height and balance factor for a node and updates the values
	 * 
	 * THIS DOES NOT RECURSIVELY UPDATE N AND ALL OF N'S CHILDREN, ONLY UPDATE N
	 * (caps because it's important! Don't kill the running time of everything!)
	 * 
	 * @param n The node whose values are to be calculated and updated
	 * @return The node passed in with updated values
	 */
	private Node<T> updateHeightAndBF(Node<T> n) {
		return null;
	}
	
	/**
	 * Determines what rotation, if any, needs to be performed on a node and does the appropriate rotation
	 * 
	 * @param n The node to potentially be rotated
	 * @return The new root of the subtree that is now balanced due to the rotation 
	 * 			(possibly the same node that was passed in) 
	 */
	private Node<T> rotate(Node<T> n) {
		return null;
	}
	
	/**
	 * Performs a left rotation on a node
	 * 
	 * @param n The node to have the left rotation performed on
	 * @return The new root of the subtree that is now balanced due to the rotation
	 */
	private Node<T> left(Node<T> n) {
		return null;
	}
	
	/**
	 * Performs a right rotation on a node
	 * 
	 * @param n The node to have the right rotation performed on
	 * @return The new root of the subtree that is now balanced due to the rotation
	 */
	private Node<T> right(Node<T> n) {
		return null;
	}
	
	/**
	 * Performs a left right rotation on a node
	 * 
	 * @param n The node to have the left right rotation performed on
	 * @return The new root of the subtree that is now balanced due to the rotation
	 */
	private Node<T> leftRight(Node<T> n) {
		return null;
	}
	
	/**
	 * Performs a right left rotation on a node
	 * 
	 * @param n The node to have the right left rotation performed on
	 * @return The new root of the subtree that is now balanced due to the rotation
	 */
	private Node<T> rightLeft(Node<T> n) {
		return null;
	}
	
	/**
	 * Checks to see if the AVL tree is empty
	 * 
	 * @return If the AVL tree is empty or not
	 */
	public boolean isEmpty() {
		return false;
	}
	
	/**
	 * Clears this AVL tree
	 */
	public void clear() {
		
	}
	
	/*
	 * Getters and Setters: Do not modify anything below this point
	 */
	
	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}
	
	public int size() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public static class Node<K extends Comparable<K>> {
		
		private K data;
		private Node<K> left, right;
		private int height;
		private int bf;
		
		public Node(K data) {
			setData(data);
		}

		public K getData() {
			return data;
		}

		public void setData(K data) {
			this.data = data;
		}
		
		public Node<K> getLeft() {
			return left;
		}
		
		public void setLeft(Node<K> left) {
			this.left = left;
		}
		
		public Node<K> getRight() {
			return right;
		}
		
		public void setRight(Node<K> right) {
			this.right = right;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public int getBf() {
			return bf;
		}

		public void setBf(int bf) {
			this.bf = bf;
		}
	}
}
