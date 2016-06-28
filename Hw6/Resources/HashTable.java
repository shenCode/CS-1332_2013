import java.util.Collection;
import java.util.Set;


public class HashTable<K,V> {

	/**
	 * The maximum load factor for this hashtable
	 */
	private final double MAX_LOAD_FACTOR = .64;

	/**
	 * The number of entries in this hashtable
	 */
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
	public V put(K key ,V value) {
		return null;
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
		return null;
	}
	
	/**
	 * @param key, never null
	 * @return true if this table contains the given key, false otherwise
	 */
	public boolean containsKey(Object key) {
		return false;
	}
	
	/**
	 * Clears this hashTable
	 */
	public void clear() {
		
	}
	
	/**
	 * @return true if this hashtable is empty, false otherwise
	 */
	public boolean isEmpty() {
		return false;
	}
	
	/**
	 * @return the value from this hashtable
	 */
	public Collection<V> values() {
		return null;
	}
	
	/**
	 * @return the unique keys from this hashtable
	 */
	public Set<K> keySet() {
		return null;
	}
	
	/**
	 * @return the unique entries from this hashtable
	 */
	public Set<Entry<K, V>> entrySet() {
		return null;
	}
	
	/**
	 * @return the size of this hashtable
	 */
	public int size() {
		return size;
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
			this.setAvailable(true);
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
