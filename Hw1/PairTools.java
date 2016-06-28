/**
 * @author Shen Yang
 *
 * Collaboration statement: I did this on my own.
 */

public class PairTools {

	/**
	 * @param pair (assume never null)
	 * @return a pair with a and b swapped from the given pair
	 */
	public static <A, B> Pair<B, A> invert(Pair<A, B> pair) {
		Pair<B,A> result = new Pair<B,A> (pair.b,pair.a);
		return result;
	}
	
	/**
	 * this is how you can use wildcards in generics
	 * 
	 * @param pair (assume never null)
	 * @return a pair containing two references to a of the given pair
	 */
	public static <A> Pair<A, A> copyA(Pair<A, ?> pair) {
		Pair<A,A> result = new Pair<A,A> (pair.a,pair.a);
		return result;
	}
	
	/**
	 * we can also nest generics and do really confusing things with them
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return a pair containing two pairs containing a, b; and c, d respectively
	 */
	public static <A, B, C, D> Pair<Pair<A, B>, Pair<C, D>> compose(A a, B b, C c, D d) {
		Pair<Pair<A,B>,Pair<C,D>> result = new Pair<Pair<A,B>,Pair<C,D>>(new Pair<A,B>(a,b),new Pair<C,D>(c,d));
		return result;
	}
}
