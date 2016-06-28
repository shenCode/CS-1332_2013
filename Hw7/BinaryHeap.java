import java.util.Comparator;

/**
 * This is an implementation of a heap that is backed by an array.
 * 
 * This implementation will accept a comparator object that can be used to
 * define an ordering of the items contained in this heap, other than the
 * objects' default compareTo method (if they are comparable). This is useful if
 * you wanted to sort strings by their length rather than their lexicographic
 * ordering. That's just one example.
 * 
 * Null should be treated as positive infinity if no comparator is provided. If
 * a comparator is provided, you should let it handle nulls, which means it
 * could possibly throw a NullPointerException, which in this case would be
 * fine.
 * 
 * If a comparator is provided that should always be what you use to compare
 * objects. If no comparator is provided you may assume the objects are
 * Comparable and cast them to type Comparable<T> for comparisons. If they
 * happen to not be Comparable you do not need to handle anything, and you can
 * just let your cast throw a ClassCastException.
 * 
 * This is a minimum heap, so the smallest item should always be at the root.
 * 
 * @param <T>
 *            The type of objects in this heap
 */
public class BinaryHeap<T> implements Heap<T> {

	/**
	 * The comparator that should be used to order the elements in this heap
	 */
	private Comparator<T> comp;

	/**
	 * The backing array of this heap
	 */
	private T[] data;

	/**
	 * The number of elements that have been added to this heap, this is NOT the
	 * same as data.length
	 */
	private int size;

	/**
	 * Default constructor, this should initialize data to a default size (11 is
	 * normally a good choice)
	 * 
	 * This assumes that the generic objects are Comparable, you will need to
	 * cast them when comparing since there are no bounds on the generic
	 * parameter
	 */
	public BinaryHeap() {
		data = (T[])(new Object[11]);
		size = 0;
		comp = new DefaultCompare();
	}

	/**
	 * Constructor that accepts a comparator to use with this heap. Also
	 * initializes data to a default size.
	 * 
	 * When a comparator is provided it should be preferred over the objects'
	 * compareTo method
	 * 
	 * If the comparator given is null you should attempt to cast the objects to
	 * Comparable as if a comparator were not given
	 * 
	 * @param comp
	 */
	public BinaryHeap(Comparator<T> comp) {
		if (comp == null)
		{
			comp = new DefaultCompare();
		}
		else
		{
			this.comp = comp;
		}
		data = (T[])(new Object[11]);
		size=0;
	}

	@Override
	public void add(T item) {
		size++;
		data[size] = item;
		if (size+1 == data.length)
		{
			resize();
		}
		siftUp(item);
	}
	
	private void resize()
	{
		T[] tmp = data;
		data = (T[])(new Object[tmp.length*2]);
		
		for (int i = 0; i < tmp.length; i++)
		{
			data[i] = tmp[i];
		}
	}
	
	private void siftUp(T item)
	{
		int index = getIndex(item), parentIndex = index/2;
		T parent = data[parentIndex];
		if (comp.compare(item, parent) < 0 && parentIndex != 0)
		{
			data[parentIndex] = item;
			data[index] = parent;
			siftUp(item);
		}		
	}
	
	private int getIndex(T item)
	{
		if (item == null)
		{
			for (int i = 1; i < size+1; i++)
			{
				if (data[i] == item)
				{
					return i;
				}
			}
		}
		else
		{
			
			for (int i = 0; i < size+1; i++)
			{
				if (data[i] == item)
				{
					return i;
				}
			}
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		// TODO Implement this.
		return size==0;
	}

	@Override
	public T peek() {
		if (isEmpty())
		{
			return null;
		}
		else
		{
			return data[1];
		}
	}

	@Override
	public T remove() {
		T tmp = data[1];
		data[1] = data[size];
		data[size] = null;
		siftDown(data[1]);
		size--;
		return tmp;
	}
	
	private void siftDown(T item)
	{
		
		int index = getIndex(item);
		int child1Index = index*2, child2Index = index*2+1;
		T child1 = null, child2 = null;
		if (child1Index <= size)
		{
			child1 = data[child1Index];
		}
		if (child2Index <= size)
		{
			child2 = data[child2Index];
		}
		if (child1 != null && child2 != null && (comp.compare(item, child2) > 0 || comp.compare(item, child1) > 0))
		{
			
			if (comp.compare(child1, child2) < 0)
			{
				data[child1Index] = item;
				data[index] = child1;
			}
			else
			{
				data[child2Index] = item;
				data[index] = child2;
			}
			siftDown(item);
		}
		else if (child1 == null && child2 != null && comp.compare(item, child2) > 0)
		{
			data[child2Index] = item;
			data[index] = child2;
			siftDown(item);
		}
		else if (child2 == null && child1 != null  && comp.compare(item, child1) > 0)
		{
			data[child1Index] = item;
			data[index] = child1;
			siftDown(item);
		}
	}
	
	private T findMin(T t1, T t2)
	{
		if (comp.compare(t1, t2) < 0)
		{
			return t1;
		}
		else
		{
			return t2;
		}
	}
	
	@Override
	public int size() {
		return size;
	}
	
	public String toString()
	{
		String tmp = "[";
		for (int i = 0; i < data.length; i++)
		{
			tmp += data[i] + ",";
		}
		tmp += "]";
		return tmp;
	}
	
	public class DefaultCompare<T> implements Comparator<T>
	{
		@SuppressWarnings("unchecked")
		public int compare(T t1, T t2)
		{
			if (t1 == null && t2 == null)
			{
				return 0;
			}
			else if (t1 == null)
			{
				return 1;
			}
			else if (t2 == null)
			{
				return -1;
			}
			else
			{
				return ((Comparable<T>) t1).compareTo(t2);
			}
		}
	}
}
