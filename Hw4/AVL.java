import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @author Johnson
 *
 * @param <T>
 */

public class AVL<T extends Comparable<T>> {
	
	private Node<T> root;
	private int size;
	
	/**
	 * Adds a data entry to the AVL tree
	 * 
	 * @param data The data entry to add
	 */
	public void add(T data) {
		if (root == null)
		{
			root = updateHeightAndBF(new Node<T>(data));
			size++;
		}
		else
		{
			add(data, root);
			root = rotate(root);
		}
	}
	
	private void add(T data, Node<T> n)
	{
		if (data == null)
		{
			while (n.right != null)
			{
				n = n.right;
			}
			n.right = updateHeightAndBF(new Node<T>(data));
			size++;
		}
		
		else if (n.data == null)
		{
			if (n.left == null)
			{
				n.left = updateHeightAndBF(new Node<T>(data));
				size++;
			}
			else
			{
				add(data, n.left);
				n.left = rotate(n.left);
			}
		}
		else
		{
			if (n.data.compareTo(data) > 0)
			{
				if (n.left == null)
				{
					n.left = updateHeightAndBF(new Node<T>(data));
					size++;
				}
				else
				{
					add(data, n.left);
					n.left = rotate(n.left);
				}
			}
		
			else if (n.data.compareTo(data) < 0)
			{
				if (n.right == null)
				{
					n.right = updateHeightAndBF(new Node<T>(data));
					size++;
				}
				else
				{
					add(data, n.right);
					n.right = rotate(n.right);
				}
			}
		}
	}
	
	/**
	 * Adds each data entry from the collection to this AVL tree
	 * 
	 * @param c The collection 
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
	 * Removes a data entry from the AVL tree
	 * 
	 * Return null if the value does not exist
	 * 
	 * @param data The data entry to be removed
	 * @return The removed data entry
	 */
	public T remove(T data) {
		if (size == 1 && root.data == null && data == null)
		{
			root = null;
			size = 0;
			return data;
		}
		
		else
		{
			T t = remove(data, root, null);
			root = rotate(root);
			return t;
		}
	}
	
	private T remove(T data, Node<T> here, Node<T> parent)
	{
		if (here == null)
		{
			return null;
		}
		
		else if (data == null)
		{
			if (here.data == null)
			{
				if (here.left == null)
				{
					parent.right = here.right;
				}
				
				else if (here.left.right == null)
				{
					here.data = here.left.data;
					here.left = here.left.left;
				}
				
				else
				{
					Node<T> t;
					here = here.left;
					while (here.right.right != null)
					{
						here = here.right;
						here.right = rotate(here.right);
					}
					t = here.right;
					t.right = here.right.left;
					here.data = t.data;
					here.left = rotate(here.left);
				}
				size--;
				return null;
			}
			else
			{
				T t = remove(data, here.right, here);
				here.right = rotate(here.right);
				return t;
			}
		}
		
		else
		{
			if (here.data.equals(data))
			{
				if (here.left == null)
				{
					if (here == root)
					{
						root = here.right;
					}
					
					else if (parent.right == here)
					{
						parent.right = here.right;
					}
					
					else
					{
						parent.left = here.right;
					}

				}
				else if (here.left.right == null)
				{
					here.data = here.left.data;
					here.left = here.left.left;
				}
				
				else
				{
					Node<T> t, x;
					x = here;
					here = here.left;
					while (here.right.right != null)
					{
						here = here.right;
						here.right = rotate(here.right);
					}
					t = here.right;
					x.data = t.data;
					here.right = null;
					x.left = rotate(x.left);
				}
			size--;
			return data;
			}
			
			else if (here.data.compareTo(data) < 0)
			{
				T t = remove(data, here.right, here);
				here.right = rotate(here.right);
				return t;
			}
			
			else
			{
				T t = remove(data, here.left, here);
				here.left = rotate(here.left);
				return t;
			}
			
			
		}
	}
	
	/**
	 * Checks if the AVL tree contains a data entry
	 * 
	 * @param data The data entry to be checked
	 * @return If the data entry is in the AVL tree 
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
	
	private Node<T> findParent(Node<T> n)
	{
		return findParent(n, root);
	}
	
	private Node<T> findParent(Node<T> n, Node<T> parent)
	{
		if (parent == root)
		{
			return null;
		}
		
		else if (n.data.compareTo(parent.left.data) < 0)
		{
			return findParent(n, parent.left);
		}
		
		else if (n.data.compareTo(parent.right.data) > 0 )
		{
			return findParent(n, parent.right);
		}
		
		else
		{
			return parent;
		}
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
	
	private Node<T> updateHeightAndBF(Node<T> n)
	{
		int leftH, rightH;
		
		if (n.left == null)
		{
			leftH = 1;
		}
		
		else
		{
			leftH = n.left.height;
		}
		
		if (n.right == null)
		{
			rightH = 1;
		}
		
		else
		{
			rightH = n.right.height;
		}
		
		if (leftH >= rightH)
		{
			n.height = leftH+1;
		}
		
		else
		{
			n.height = rightH+1;
		}

		n.bf = leftH - rightH;
		return n;
	}

	
	/**
	 * Determines what rotation, if any, needs to be performed on a node and does the appropriate rotation
	 * 
	 * @param n The node to potentially be rotated
	 * @return The new root of the subtree that is now balanced due to the rotation 
	 * 			(possibly the same node that was passed in) 
	 */
	private Node<T> rotate(Node<T> n) {
		if (n != null)
		{
			n = updateHeightAndBF(n);
			
			if (n.bf > 1)
			{
				if (n.left.bf > 0)
				{
					return right(n);
				}
				else
				{
					return leftRight(n);
				}
			}
			
			else if (n.bf < -1)
			{
				if (n.right.bf > 0)
				{
					return rightLeft(n);
				}
				else
				{
					return left(n);
				}
			}
		}
		return n;
	}

	
	/**
	 * Performs a left rotation on a node
	 * 
	 * @param n The node to have the left rotation performed on
	 * @return The new root of the subtree that is now balanced due to the rotation
	 */
	private Node<T> left(Node<T> n) {
		Node<T> tmp = n.right;
		n.right = tmp.left;
		tmp.left = n;
		n = updateHeightAndBF(n);
		tmp = updateHeightAndBF(tmp);
		return tmp;
	}
	
	/**
	 * Performs a right rotation on a node
	 * 
	 * @param n The node to have the right rotation performed on
	 * @return The new root of the subtree that is now balanced due to the rotation
	 */
	private Node<T> right(Node<T> n) {
		Node<T> tmp = n.left;
		n.left = tmp.right;
		tmp.right = n;
		n = updateHeightAndBF(n);
		tmp = updateHeightAndBF(tmp);
		return tmp;
	}
	
	/**
	 * Performs a left right rotation on a node
	 * 
	 * @param n The node to have the left right rotation performed on
	 * @return The new root of the subtree that is now balanced due to the rotation
	 */
	private Node<T> leftRight(Node<T> n) {
		n.left = left(n.left);
		return right(n);
	}
	
	/**
	 * Performs a right left rotation on a node
	 * 
	 * @param n The node to have the right left rotation performed on
	 * @return The new root of the subtree that is now balanced due to the rotation
	 */
	private Node<T> rightLeft(Node<T> n) {

		n.right = right(n.right);
		return left(n);
	}
	
	/**
	 * Checks to see if the AVL tree is empty
	 * 
	 * @return If the AVL tree is empty or not
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Clears this AVL tree
	 */
	public void clear() {
		root = null;
		
	}
	
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
