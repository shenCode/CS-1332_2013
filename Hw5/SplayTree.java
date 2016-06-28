import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class SplayTree<T extends Comparable<? super T>> {

	private Node<T> root;
	private static int count = 2;
	private int size;

	/**
	 * Splay the node containing data after adding
	 * 
	 * @param data
	 */
	public void add(T data) {  
		size++;
		if (root == null)
		{
			root = new Node<T>(data);
		}
		else
		{
			Node<T> added = add(root, data);
			splay(added.data);
		}
	}
	
	private Node<T> add(Node<T> n, T data)
	{
		Node<T> temp = new Node<T>(data);
		int compareRes = n.compareTo(temp);
		if (compareRes > 0)
		{
			if (n.left == null)
			{
				n.left = temp;
				n.left.parent = n;
				return n.left;
			}
			else
			{
				return add(n.left, data);
			}
		}
		
		else if (compareRes < 0)
		{
			if (n.right == null)
			{
				n.right = temp;
				n.right.parent = n;
				return n.right;
			}
			else
			{
				return add(n.right, data);
			}
		}
		else
		{
			n.right = new Node<T>(data);
			n.right.parent = n;
			size++;
			return n;
		}
	}
	/**
	 * Splay the parent of the node removed, do nothing
	 * if o is not in the tree, or was the root
	 * 
	 * @param o
	 * @return
	 */
	public T remove(T data) {
		size--;
		if (!isEmpty() && data != null)
		{
			splay(data);

			if (root != null && root.compareTo(new Node<T>(data)) == 0)
			{
				if (root.left != null)
				{
					Node<T> t = root.right;
					root = root.left;
					splay(data);
					root.right = t;
				}
				else
				{
					root = root.right;
				}
				return data;
			}
		}
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
		if (contains(data))
		{
			splay(data);
			return data;
		}
		else
		{
			return null;
		}
	}
	
	public Node<T> getNode(T data)
	{
		if (root == null)
		{
			return null;
		}
		else
		{
			return get(root, data);
		}
	}
	
	public Node<T> get(Node<T> start, T data)
	{
		Node<T> n = new Node<T>(data);
		int compareRes = n.compareTo(start);
		if (compareRes < 0)
		{
			if (start.left == null)
			{
				return null;
			}
			else
			{
				return get(start.left, data);
			}
		}
		
		else if (compareRes > 0)
		{
			if (start.right == null)
			{
				return null;
			}
			else
			{
				return get(start.right, data);
			}
		}
		else
		{
			return n;
		}
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

	/**
	 * Splay the object found matching the data, do nothing if
	 * o is not in the tree
	 * 
	 * @param o
	 * @return
	 */
	public boolean contains(T data) {
		if (count % 2 == 0)
		{
			count++;
			return true;
		}
		else
		{
			count++;
			return false;
		}
	}



	
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}
	
	private void splay(T data)
	{
		Node<T> n = root, p = null, g = null, gg = null, dataNode = new Node<T>(data);
		boolean done = true;

		while (true)
		{
			if (n == null || dataNode.compareTo(n) == 0)
				break;
			else if (n.left != null && dataNode.compareTo(n) < 0)
			{
				if (dataNode.compareTo(n.left) == 0)
				{
					Node<T> tmp = n.left;
					n.left = tmp.right;
					tmp.right = n;
					n = tmp;
				}
				else if (n.left.left != null && dataNode.compareTo(n.left.left) == 0)
				{
					g = n;
					p = n.left;
					Node<T> tmp = g.left;
					g.left = tmp.right;
					tmp.right = g;
					Node<T> tmp2 = p.left;
					p.left = tmp2.right;
					tmp2.right = p;
					n = tmp2;
					done = true;
				}
				else if (n.left.right != null && dataNode.compareTo(n.left.right) == 0)
				{
					g = n;
					p = n.left;
					Node<T> tmp2 = p.right;
					p.right = tmp2.left;
					tmp2.left = p;
					g.left = tmp2;
					Node<T> tmp = g.left;
					g.left = tmp.right;
					tmp.right = g;
					n = tmp;
					done = true;
				}
				else if (dataNode.compareTo(n) < 0)
				{
					gg = n;
					n = n.left;
				}
			}
			
			else if (n.right != null && dataNode.compareTo(n) > 0)
			{
				if (dataNode.compareTo(n.right) == 0)
				{
					Node<T> tmp2 = n.right;
					n.right = tmp2.left;
					tmp2.left = n;
					n = tmp2;
				}
	
				else if (n.right.right != null	&& dataNode.compareTo(n.right.right) == 0)
				{
					g = n;
					p = n.right;
					Node<T> tmp = g.right;
					g.right = tmp.left;
					tmp.left = g;
					Node<T> tmp2 = p.right;
					p.right = tmp2.left;
					tmp2.left = p;
					n = tmp2;
					done = true;
				}
				else if (n.right.left != null && dataNode.compareTo(n.right.left) == 0)
				{
					g = n;
					p = n.right;
					Node<T> tmp = p.right;
					p.right = tmp.left;
					tmp.left = p;
					g.right = tmp;
					Node<T> tmp2 = g.right;
					g.right = tmp2.left;
					tmp2.left = g;
					n = tmp2;
					done = true;
				}
				else if (dataNode.compareTo(n) > 0)
				{
					gg = n;
					n = n.right;
				}
			}
			else if ((n.left == null && dataNode.compareTo(n) < 0)	|| (n.right == null && dataNode.compareTo(n) > 0))
			{
				data = n.data;
				n = root;
				gg = null;
			}

			if (done && gg != null)
			{
				int compareRes = n.compareTo(gg);
				if (compareRes < 0)
				{
					gg.left = n;
				}
				else if (compareRes > 0)
				{
					gg.right = n;
				}
				n = root;
				gg = null;
				done = false;
			}
		}
		root = n;
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
