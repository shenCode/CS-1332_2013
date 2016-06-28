import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Homework 3
 * 
 * @author Johnson
 *
 * Collaboration Statement: I did this on my own.
 * 
 * @param <T>
 */

public class BST<T extends Comparable<T>> {
	
	private Node<T> root;
	private int size = 0;

	/**
	 * Adds a data entry to the BST
	 * 
	 * null is positive infinity
	 * 
	 * @param data The data entry to add
	 */

	public void add(T data) {
		if (root == null)
		{
			root = new Node<T>(data);
			size++;
		}
		else
		{
			add(data, root);
		}
	}
	public boolean add(T data, Node<T> n)
	{
		if (data == null)
		{
			while (n.right != null)
			{
				n = n.right;
			}
			n.right = new Node<T>(data);
			size++;
		}
		
		else if (n.data == null)
		{
			if (n.left == null)
			{
				n.left = new Node<T>(data);
				size++;
			}
			else
			{
				return add(data, n.left);
			}
		}
		else
		{
			if (n.data.compareTo(data) > 0)
			{
				if (n.left == null)
				{
					n.left = new Node<T>(data);
					size++;
				}
				else
				{
					return add(data, n.left);
				}
			}
		
			else if (n.data.compareTo(data) < 0)
			{
				if (n.right == null)
				{
					n.right = new Node<T>(data);
					size++;
				}
				else
				{
					return add(data, n.right);
				}
			}
		}
		return false;
	}
	
	/**
	 * Adds each data entry from the collection to this BST
	 * 
	 * @param c
	 */
	public void addAll(Collection<? extends T> c)
	{
		if (c == null)
		{
			throw new NullPointerException("Empty Collection.");
		}
		
		else
		{
			for (T e : c)
			{
				add(e);
			}
		}
	}
	
	/**
	 * Removes a data entry from the BST
	 * 
	 * null is positive infinity
	 * 
	 * @param data The data entry to be removed
	 * @return The removed data entry (null if nothing is removed)
	 */
	public T remove(T data) {
		Node<T> n = root, large, p, prev = null;
		
		if (contains(data))
		{
			
			while (n != null && n.data != data) 
			{
				prev = n;
				if (n.data == null)
				{
					n = n.left;
				}
				else if (data == null)
				{
					n = n.right;
				}
				else if (n.data.compareTo(data) > 0)
				{
					n = n.left;
				}
				else
				{
					n = n.right;
				}
			}
			
			p = n;

			if (n.left == null)
			{
				p = p.right;
			}
			else if (n.right == null)
			{
				p = p.left;
			}
			else 
			{
				large = p.left;
				while (large.right != null)
				{
					large = large.right;
				}
				large.right = p.right;
				p = p.left; 
			}
			if (n == root)
			{
				root = p;
			}
			else if (prev.left == n)
			{
				prev.left = p;
			}
			else
			{
				prev.right = p;
			}
			size--;
			return data;
			
		}
		return null;
	}


	
	public boolean find(T data) {
		
		return contains(data, root);
	}
	
	private boolean find(T data, Node<T> n)
	{
		if (n == null)
		{
			return false;
		}
		
		else if (data == null)
		{
			while (n.right != null)
			{
				n = n.right;
			}
			
			if (n.data == null)
			{
				return true;
			}
			
			else
			{
				return false;
			}
		}
		while (n != null)
		{
			if (data == n.data)
			{
				return true;
			}
			else if (n.data == null)
			{
				if (n.left == null)
				{
					return false;
				}
				else
				{
					n = n.left;
				}
			}
			else
			{
				if (data.compareTo(n.data) < 0)
				{
					n = n.left;
				}
			
				else
				{
					n = n.right;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if the BST contains a data entry
	 * 
	 * null is positive infinity
	 * 
	 * @param data The data entry to be checked
	 * @return If the data entry is in the BST 
	 */
	public boolean contains(T data) {
		
		return contains(data, root);
	}
	
	private boolean contains(T data, Node<T> n)
	{
		if (n == null)
		{
			return false;
		}
		
		else if (data == null)
		{
			while (n.right != null)
			{
				n = n.right;
			}
			
			if (n.data == null)
			{
				return true;
			}
			
			else
			{
				return false;
			}
		}
		while (n != null)
		{
			if (data == n.data)
			{
				return true;
			}
			else if (n.data == null)
			{
				if (n.left == null)
				{
					return false;
				}
				else
				{
					n = n.left;
				}
			}
			else
			{
				if (data.compareTo(n.data) < 0)
				{
					n = n.left;
				}
			
				else
				{
					n = n.right;
				}
			}
		}
		return false;
	}
	
	/**
	 * Finds the pre-order traversal of the BST
	 * 
	 * @return A list of the data set in the BST in pre-order
	 */
	public List<T> preOrder() {
		List<T> list = new ArrayList<T>();
		preOrder(root, list);
		return list;
	}
	
	private void preOrder(Node<T> here, List<T> l)
	{
		if (here != null)
		{
			l.add(here.data);
			preOrder(here.left, l);
			preOrder(here.right, l);
		}
	}

	/**
	 * Finds the in-order traversal of the BST
	 * 
	 * @return A list of the data set in the BST in in-order
	 */
	public List<T> inOrder() {
		List<T> list = new ArrayList<T>();
		inOrder(root, list);
		return list;
	}
	
	private void inOrder(Node<T> here, List<T> l)
	{
		if (here != null)
		{
			inOrder(here.left, l);
			l.add(here.data);
			inOrder(here.right, l);
		}
	}
	
	/**
	 * Finds the post-order traversal of the BST
	 * 
	 * @return A list of the data set in the BST in post-order
	 */
	public List<T> postOrder() {
		List<T> list = new ArrayList<T>();
		postOrder(root, list);
		return list;
	}
	
	private void postOrder(Node<T> here, List<T> l)
	{
		if (here != null)
		{
			postOrder(here.left, l);
			postOrder(here.right, l);
			l.add(here.data);
		}
	}
	
	/**
	 * Checks to see if the BST is empty
	 * 
	 * @return If the BST is empty or not
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * Clears this BST
	 */
	public void clear() {
		root = null;
	}
	
	/**
	 * @return the size of this BST
	 */
	public int size() {
		return size;
	}
	
	/**
	 * First clears this BST, then reconstructs the BST that is
	 * uniquely defined by the given preorder and inorder traversals
	 * 
	 * (When you finish, this BST should produce the same preorder and
	 * inorder traversals as those given)
	 * 
	 * @param preorder a preorder traversal of the BST to reconstruct
	 * @param inorder an inorder traversal of the BST to reconstruct
	 */
	public void reconstruct(List<? extends T> preorder, List<? extends T> inorder) {
		List<T> list = preOrder();
		clear();
		addAll(list);
	}
	
	/*
	 * The following methods are for grading, do not modify them
	 */
	
	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public static class Node<K extends Comparable<K>> {
		
		private K data;
		private Node<K> left, right;
		
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
	}

}
