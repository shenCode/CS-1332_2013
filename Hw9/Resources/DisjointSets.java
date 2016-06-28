import java.util.Set;

public class DisjointSets<T> {

	/**
	 * @param setItems
	 *            the initial items (sameSet and merge will never be called with
	 *            items not in this set, this set will never contain null
	 *            elements)
	 */
	public DisjointSets(Set<T> setItems) {
		// TODO
	}

	/**
	 * @param u
	 * @param v
	 * @return true if the items given are in the same set, false otherwise
	 */
	public boolean sameSet(T u, T v) {
		// TODO
		return false;
	}

	/**
	 * merges the sets u and v are in, do nothing if they are in the same set
	 * 
	 * @param u
	 * @param v
	 */
	public void merge(T u, T v) {
		// TODO
	}
}
