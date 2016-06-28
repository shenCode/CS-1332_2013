
/**
 * This class extends LinkedList, but there's a twist. Read the documentation
 * for each method. Note that the data here is Comparable.
 *
 * @author: Shen Yang
 *
 * Collaboration statement: I did this assignment on my own.
 */
public class TwistList<E extends Comparable<E>> extends LinkedList<E> {

	/**
	 * Add a piece of data either at the front of the list if the data
	 * is less than the head. If the data to be added is not less then 
	 * the data at the front of the list then find the first place in the
	 * list where the data is between two other points of data. If this is
	 * never true then place the new piece of data at the end of the list.
	 * 
	 * Last of all call swing with the index at which the new piece of data was added.
	 */
	@Override
	public void add(E e)
	{
		int p = 0;
		boolean done = false;
		Node<E> newNode = new Node<E>(e);
		
		if (isEmpty())
		{
			super.add(e);
			done = true;
		}
		
		else if (e.compareTo(head.getData()) < 0)
		{
			newNode.setNext(head);
			head = newNode;
			done = true;
			size++;
		}
		
		else
		{
			int p1 = 0;
			Node<E> here = head;
			Node<E> here2 = head;
			while (p1 < size()-1)
			{
				if (e.compareTo(get(p1)) < 0)
				{
					for (int i = 0; i < p1; i++)
					{
						here = here.getNext();
					}
					newNode.setNext(here);
					
					for (int i = 0; i < p1-1; i++)
					{
						here2 = here2.getNext();
					}
					here2.setNext(newNode);
					size++;
					done = true;
					break;
				}
				p1++;
			}
			
			if (!done)
			{
				Node<E> here3 = head;
				for (int i = 0; i < size-1; i++)
				{
					here3 = here3.getNext();
				}
				
				here3.setNext(newNode);
				newNode.setNext(head);
				p = size;
				size++;
			}
			swing(p);
		}
	}
	
	/**
	 * Reverses the order of the list between the start and stop index inclusively.
	 * 
	 * Assume the indices given are valid and start <= stop
	 * 
	 * @param start The beginning index of the sub section to be reversed
	 * @param stop The end index (inclusive) of the sub section to be reversed
	 */
	public void reverse(int start, int stop) {
		
		Node<E> startNode = head;
		Node<E> stopNode = head;
		
		for (int i = 0; i < start; i++)
		{
			startNode = startNode.getNext();
		}
		
		for (int i = 0; i < stop; i++)
		{
			stopNode = stopNode.getNext();
		}
		
		while (start < stop)
		{
			E d = startNode.getData();
			startNode.setData(stopNode.getData());
			stopNode.setData(d);
			start++;stop--;
		}
	}
	
	/**
	 * This method will take in an index and move everything after 
	 * that index to the front of the list
	 * 
	 * Assume the index given is valid
	 * 
	 * @param index The index at which to cut the list
	 */
	public void flipFlop(int index) {

		for (int i = 0; i < index + 1; i++)
		{
			head = head.getNext();
		}
	}
	
	/**
	 * This method will reverse the order of the first half of the list up to 
	 * the index argument (inclusive), and also reverse the second half of the 
	 * list from index + 1 to the end of the list
	 * 
	 * Assume the index given is valid, however the second half may be empty
	 * 
	 * @param index The index to swing around
	 */
	public void swing(int index) {
		reverse(0, index);
		if (index+1 < size())
		{
			reverse(index +1, size-1);
		}
	}
}
