
public class SplayTree<T extends Comparable<? super T>> {

	private Node<T> root;

	private int size;

	/**
	 * Splay the node containing data after adding
	 * 
	 * @param data
	 */
	public void add(T data) {  
		// TODO Auto-generated method stub
	}

	/**
	 * Splay the parent of the node removed, do nothing
	 * if o is not in the tree, or was the root
	 * 
	 * @param o
	 * @return
	 */
	public T remove(T data) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Splay the object found matching the data, do nothing
	 * if o is not in the tree
	 * 
	 * @param o
	 * @return
	 */
	public T get(T data) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Splay the object found matching the data, do nothing if
	 * o is not in the tree
	 * 
	 * @param o
	 * @return
	 */
	public boolean contains(T data) {
		// TODO Auto-generated method stub
		return false;
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * These methods are for grading, don't modify them
	 */

	public void setSize(int size) {
		this.size = size;
	}

	public Node<T> getRoot() { 
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}

	public static class Node<E extends Comparable<? super E>> implements Comparable<Node<E>>{

		private Node<E> parent, left, right;
		private E data;

		public Node(E data) {
			this.data = data;
		}

		public Node<E> getParent() {
			return parent;
		}

		public void setParent(Node<E> parent) {
			this.parent = parent;
		}

		public Node<E> getLeft() {
			return left;
		}

		public void setLeft(Node<E> left) {
			this.left = left;
		}

		public Node<E> getRight() {
			return right;
		}

		public void setRight(Node<E> right) {
			this.right = right;
		}

		public E getData() {
			return data;
		}
		
		public void setData(E data) {
			this.data = data;
		}
		
		@Override
		public int compareTo(Node<E> tht) {
			if (data == null && tht.data == null) return 0;
			if (tht.data == null) return -1332;
			if (data == null) return 1332;
			return data.compareTo(tht.data);
		}
	}

}
