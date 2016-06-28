

import java.util.Comparator;

/**
 * @author Your Name
 */
public class SimpleClass {

	/**
	 * @return the string "Hello World"
	 */
	public static String hello() {
		return null;
	}
	
	/**
	 * @param name someone's name
	 * @return the string "Hello " + name
	 */
	public static String hello(String name) {
		return null;
	}
	
	/**
	 * this is how you use generic types in static methods
	 * 
	 * @param thing something of type T
	 * @return the string "Hello " + thing
	 */
	public static <T> String helloThing(T thing) {
		return null;
	}
	
	
	/**
	 * you should always use .equals() when checking equality, NOT == or .compareTo()
	 * 
	 * @param a
	 * @param b
	 * @return true if the objects are equal, check using the equals method
	 */
	public static boolean same(String a, String b) {
		return false;
	}
	
	/**
	 * this is how you use a generic that is comparable
	 * 
	 * @param a (assume never null)
	 * @param b (assume never null)
	 * @return true if a < b, check by using compareTo
	 */
	public static <T extends Comparable<T>> boolean strictlyLess(T a, T b) {
		return false;
	}
	
	/**
	 * this is how you use a comparator
	 * 
	 * @param a
	 * @param b
	 * @param comparator
	 * @return true if a < b, check using the comparator
	 */
	public static <T> boolean strictlyLess(T a, T b, Comparator<T> comparator) {
		return false;
	}
	
}
