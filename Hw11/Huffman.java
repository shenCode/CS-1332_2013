import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Huffman {
	
	/**
	 * Builds a frequency map of characters for the given string.
	 * 
	 * This should just be the count of each character.
	 * 
	 * @param s
	 * @return
	 */
	public static Map<Character, Integer> buildFrequencyMap(String s) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		final int INITIAL_VALUE = 1;
		for (int i = 0; i < s.length(); i++)
		{
			char tmp = s.charAt(i);
			if (map.containsKey(tmp))
			{
				int t = map.get(tmp);
				t++;
				map.remove(s);
				map.put(tmp, t);
			}
			else
			{
				map.put(tmp, INITIAL_VALUE);
			}
		}
		return map;
	}
	
	/**
	 * Build the Huffman tree using the frequencies given.
	 * 
	 * The frequency map will not necessarily come from the buildFrequencyMap() method.
	 * 
	 * @param freq
	 * @return
	 */
	public static Node buildHuffmanTree(Map<Character, Integer> freq) {
		List<Integer> values = new ArrayList<Integer>(freq.values());
		Collections.sort(values, new Comparator<Integer>()
				{
					public int compare(Integer x, Integer y)
					{
						return x-y;
					}
				});	
		LinkedList<Node> nodeList = new LinkedList<Node>();
		for (int i = 0; i < values.size(); )
		{
			char c = getKey(freq, values.get(0));
			Node tmp = new Node(c, values.get(0));
			nodeList.add(tmp);
			values.remove(0);
			freq.remove(c);
		}
		Collections.sort(nodeList, new Comparator<Node>()
				{
					public int compare(Node x, Node y)
					{
						return x.compareTo(y);
					}
				});	
		
		while (nodeList.size() != 1)
		{
			Node newNode = new Node(nodeList.poll(), nodeList.poll());
			nodeList.add(newNode);
			Collections.sort(nodeList, new Comparator<Node>()
					{
						public int compare(Node x, Node y)
						{
							return x.frequency-y.frequency;
						}
					});	
		}
		return nodeList.poll();
	}
	
	public static Character getKey(Map<Character, Integer> freq, Integer value)
	{
		for (Character ch : freq.keySet())
		{
			if (value.equals(freq.get(ch)))
			{
				return ch;
			}
		}
		return null;
	}
	
 	/**
 	 * Traverse the tree and extract the encoding for each character in the tree
 	 * 
 	 * The tree provided will be a valid huffman tree but may not come from the buildHuffmanTree() method.
 	 * 
 	 * @param tree
 	 * @return
 	 */
 	public static Map<Character, EncodedString> buildEncodingMap(Node tree) {
 		Map<Character, EncodedString> map = new HashMap<Character, EncodedString>();
 		EncodedString e = new EncodedString();
 		findNode(tree, map, e);
 		return map;
 	}
 	
 	public static void findNode(Node tree, Map<Character, EncodedString> map, EncodedString e)
 	{
 		if (tree.character == 0)
 		{
 			EncodedString left = new EncodedString();
 			EncodedString right = new EncodedString();
 			left.concat(e);
 			right.concat(e);
 			Node leftNode = tree.left;
 			Node rightNode = tree.right;
 			left.zero();
 			right.one();
 			findNode(leftNode, map, left);
 			findNode(rightNode, map, right);
 		}
 		else
 		{
 			map.put(tree.character, e);
 		}
 	}
 	
	/**
	 * Encode each character in the string using the map provided.
	 * 
	 * If a character in the string doesn't exist in the map ignore it.
	 * 
	 * The encoding map may not necessarily come from the buildEncodingMap() method, but will be correct
	 * for the tree given to decode() when decoding this method's output.
	 * 
	 * @param encodingMap
	 * @param s
	 * @return
	 */
	public static EncodedString encode(Map<Character, EncodedString> encodingMap, String s) {
		EncodedString result = new EncodedString();
		for (int i = 0; i < s.length(); i++)
		{
			EncodedString e = encodingMap.get(s.charAt(i));
			result.concat(e);
		}
		return result;
	}
	
	/**
	 * Decode the encoded string using the tree provided.
	 * 
	 * The encoded string may not necessarily come from encode, but will be a valid string for the given tree.
	 * 
	 * (tip: use StringBuilder to make this method faster -- concatenating strings is SLOW)
	 * 
	 * @param tree
	 * @param es
	 * @return
	 */
	public static String decode(Node tree, EncodedString es) {
		StringBuilder result = new StringBuilder();
		Node initial = tree;
		for (byte character : es)
		{
			if (character == 0)
			{
				tree = tree.left;
			}
			else if (character == 1)
			{
				tree = tree.right;
			}
			if (tree.character != 0)
			{
				result.append(tree.character);
				tree = initial;
			}
		}
		return result.toString();
	}
}
