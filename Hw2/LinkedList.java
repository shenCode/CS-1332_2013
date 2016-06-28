/**
 * @author: Shen Yang
 */
import java.util.Collection;

/**
 * This is a circular, singly linked list.
 */
public class LinkedList<E> implements List<E> {

	protected Node<E> head;

	protected int size = 0;

	@Override
	public void add(E e) { // Done
		
		Node<E> newNode = new Node<E>(e);
		Node<E> here = head;
		// TODO Auto-generated method stub
		if (size == 0)
		{
			head = newNode;
			size = 1;
		}
		
		else
		{
			while (here.getNext() != head)
			{
				here = here.getNext();
			}
			
			here.setNext(newNode);

			size++;
		}
		newNode.setNext(head);
	}

	/*
	 * You will want to look at Iterator, Iterable, and 
	 * how to use a for-each loop for this method.
	 */
	@Override
	public void addAll(Collection<? extends E> c)
	{
		if (c == null)
		{
			throw new NullPointerException("Empty Collection.");
		}
		
		else
		{
			for (E e : c)
			{
				add(e);
			}
		}
	}

	@Override
	public void clear() { // Done
		head = null;
		size = 0;
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contains(Object o) { // Done
		
		return (indexOf(o) > 0);
		
		/**
		Node<E> here = head;

		if (here.equals(o))
		{
			return true;
		}
		while (here.getNext() != head)
		{
			if (here.getData().equals(o))
			{
				return true;
			}
			here = here.getNext();
		}
		// TODO Auto-generated method stub
		return false;
		**/
	}

	@Override
	public E get(int index) { // Done
		Node<E> here = head;
		if (index >= size || index < 0)
		{
			throw new IndexOutOfBoundsException("WARNING, index Out of Bounds.");
		}
		for (int i = 0; i < index; i++)
		{
			here = here.getNext();
		}

		return here.getData();
	}

	@Override
	public int indexOf(Object o) { // Done
		Node<E> here = head;
		
		for (int i = 0; i < size; i++)
		{
			if (here.getData().equals(o))
			{
				return i;
			}
			here = here.getNext();
		}
		return -1;
	}

	@Override
	public boolean isEmpty() { // Done
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public E remove(int index) { // Done
		Node<E> here = head;
		E temp = null;
		if (index >= size || index < 0)
		{
			throw new IndexOutOfBoundsException("WARNING, index Out of Bounds.");
		}
		
		if (index == 0)
		{
			E e = head.getData();
			head = head.getNext();
			size--;
			for (int i = 0; i < size; i++)
			{
				here = here.getNext();
			}
			here.setNext(head);
			
			return e;
		}
		
		for (int i = 0; i < index-1; i++)
		{
			here = here.getNext();
		}
		
		Node<E> nextNode = here.getNext();
		here.setNext(nextNode.getNext());
		size--;
		return nextNode.getData();
	}

	@Override
	public E remove(Object o) { // Done
		
		if (contains(o))
		{
			return remove(indexOf(o));
		}
		
		else
		{
			return null;
		}
	}

	@Override
	public E set(int index, E e) {
		
		Node<E> newNode = new Node<E>(e);
		Node<E> here = head;

		if (size < index || index < 0)
		{
			throw new IndexOutOfBoundsException("Index Out of Bounds.");
		}
		
		for (int i = 0; i < index-1; i++)
		{
			here = here.getNext();
		}
		E old = here.getData();
		here.setData(e);
		
		return old;
		
	}
	
	@Override
	public int size() { // Done
		// TODO Auto-generated method stub
		return size;
	}
	
	public String toString()
	{
		String result = "[";
		Node<E> here = head;
		for (int i = 0; i < size; i++)
		{
			System.out.println(here.getData());
			result += here + " ";
			here = here.getNext();
		}
		return result + "]";
	}

	/*
	 * The following methods are for grading. Do not modify them, and you do not
	 * need to use them.
	 */

	public void setSize(int size) {
		this.size = size;
	}

	public Node<E> getHead() {
		return head;
	}

	public void setHead(Node<E> head) {
		this.head = head;
	}
}
