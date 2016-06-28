import java.util.*;


public class HashTable<K,V> {

	/**
	 * The maximum load factor for this hashtable
	 */
	private final double MAX_LOAD_FACTOR = .64;

	/**
	 * The number of entries in this hashtable
	 */
	private int totalSize;
	private int size;

	/**
	 * The underlying array for this hashtable
	 */
	private Entry<K,V>[] table;
	
	/**
	 * Puts the key value pair into the table. If the
	 * key already exists in the table, replace the
	 * old value with the new one and return the old value
	 * 
	 * @param key, never null
	 * @param value, possibly null
	 * @return the replaced value, null if nothing existed previously
	 */
	public HashTable()
	{
		size = 0;
		totalSize = 11;
		table = new Entry[totalSize];
	}
	public V put(K key ,V value) {
		Entry<K, V> newEntry = new Entry(key, value);
		int hashedKey = Math.abs(compress(key.hashCode()));
		int tmp = hashedKey-1;
		while (tmp != hashedKey)
		{
			if (table[hashedKey] == null || table[hashedKey].available)
			{
				table[hashedKey] = newEntry;
				checkLoad();
				size++;
				return null;
			}
			else if (table[hashedKey].key == key)
			{
				V tmpV = table[hashedKey].value;
				table[hashedKey] = newEntry;
				size++;
				return tmpV;
			}
			if (hashedKey == totalSize-1)
			{
				hashedKey = 0;
			}
			hashedKey++;
		}
		return null;
	}
	
	private int compress(int tmp)
	{
		return tmp % totalSize;
	}
	
	private void checkLoad()
	{
		int count = 0;
		for (int i = 0; i < table.length; i++)
		{
			if (table[i] != null)
			{
				count++;
			}
		}

		if ((double)count/table.length >= MAX_LOAD_FACTOR)
		{
			totalSize*=2;
			Entry<K,V>[] tmp = new Entry[totalSize];
			for (int i = 0; i < table.length; i++)
			{
				if (table[i] != null)
				{
					if (!table[i].available)
					{
						int hashedKey = compress(Math.abs(table[i].key.hashCode()));
						tmp[hashedKey] = table[i];
					}
				}
			}
			table = tmp;
		}
	}
	
	/**
	 * Removes the entry containing the given key
	 * 
	 * (remember that all objects have a hashCode method)
	 * 
	 * @param key, never null
	 * @return the value of the removed entry
	 */
	public V remove(Object key) {
		Entry<K,V> tmp = getO(key);
		if (tmp != null)
		{
			tmp.available = true;
			size--;
			return tmp.value;
		}
		return null;
	}
	
	private Entry<K,V> getO(Object key) {
		int hash = compress(Math.abs(key.hashCode()));
		while (table[hash] != null)
		{
			if (table[hash].key == key && !table[hash].available)
			{
				return table[hash];
			}
			hash++;
			if (hash == totalSize-1)
			{
				hash = 0;
			}
			
			if (table[hash+1] == null)
				break;
		}
		return null;
	}
	
	
	/**
	 * Gets the value of the entry given a specific key
	 * 
	 * (remember that all objects have a hashCode method)
	 * 
	 * @param key, never null
	 * @return
	 */
	public V get(Object key) {
		int hash = compress(Math.abs(key.hashCode()));
		int tmp = hash-1;
		while (table[hash] != null && tmp != hash)
		{
			if (table[hash].key == key && !table[hash].available)
			{
				return table[hash].value;
			}
			hash++;
			if (hash == table.length-1)
			{
				hash = 0;
			}
			
			if (table[hash+1] == null || hash == tmp)
				break;
		}
		return null;
	}
	
	/**
	 * @param key, never null
	 * @return true if this table contains the given key, false otherwise
	 */
	public boolean containsKey(Object key) {
		if (get(key) == null)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Clears this hashTable
	 */
	public void clear() {
		totalSize = 11;
		size = 0;
		table = new Entry[totalSize];
	}
	
	/**
	 * @return true if this hashtable is empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * @return the value from this hashtable
	 */
	public Collection<V> values() {
		ArrayList<V> res = new ArrayList<V>();
		for (int i = 0; i < table.length; i++)
		{
			if (table[i] != null)
			{
				if (!table[i].available)
				{
					res.add(table[i].value);
				}
			}
		}
		return res;
	}
	
	/**
	 * @return the unique keys from this hashtable
	 */
	public Set<K> keySet() {
		Set<K> res = new HashSet<K>();
		for (int i = 0; i < table.length; i++)
		{
			if (table[i] != null)
			{
				if (!table[i].available)
				{
					res.add(table[i].key);
				}
			}
		}
		return res;
	}
	
	/**
	 * @return the unique entries from this hashtable
	 */
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> res = new HashSet<Entry<K, V>>();
		for (int i = 0; i < table.length; i++)
		{
			if (table[i] != null)
			{
				if (!table[i].available)
				{
					res.add(table[i]);
				}
			}
		}
		return res;
		
	}
	
	/**
	 * @return the size of this hashtable
	 */
	public int size() {
		return totalSize;
	}
	
	public String toString()
	{
		String res = "[";
		for (int i = 0; i < table.length; i++)
		{
			if (table[i] != null)
			{
				res = res + table[i].value + ", ";
				if (table[i].available)
				{
					System.out.println(table[i].value + " " + table[i].available);
				}
			}
			else
				res = res + "null" + ", ";
		}
		res += "]";
		return res;
	}

	/*
	 * Don't modify any code below this point
	 */
	
	public void setSize(int size) {
		this.size = size;
	}

	public Entry<K,V>[] getTable() {
		return table;
	}

	public void setTable(Entry<K,V>[] table) {
		this.table = table;
	}

	public double getMaxLoadFactor() {
		return MAX_LOAD_FACTOR;
	}

	public static class Entry<K,V> {
		private K key;
		private V value;
		private boolean available;
		
		public Entry(K key, V value) {
			this.setKey(key);
			this.setValue(value);
			this.setAvailable(false);
		}
		
		public void setKey(K key) {
			this.key = key;
		}
		
		public K getKey() {
			return this.key;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
		
		public V getValue() {
			return this.value;
		}

		public boolean isAvailable() {
			return available;
		}

		public void setAvailable(boolean available) {
			this.available = available;
		}
	}
}
