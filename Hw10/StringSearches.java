import java.util.ArrayList;

public class StringSearches {

	/**
	 * Return a table for use with Boyer-Moore.
	 * 
	 * map[c] = the length - 1 - last index of c in the needle
	 * map[c] = the length if c doesn't appear in the needle
	 * 
	 * the map should have an entry for every character, 0 to Character.MAX_VALUE
	 */
	public static int[] buildCharTable(String needle)
	{
		int[] map = new int[Character.MAX_VALUE + 1];
		
		for(int i=0; i < needle.length(); i++)
		{
			char ch = needle.charAt(i);
			String str=""+ch;
			if (needle.contains(str)) {
				map[ch] = max(needle.length()-needle.lastIndexOf(needle.charAt(i))-1, 1);
			}
		}	
		
		for(int i=0;i<map.length-needle.length();i++){
			if(map[i]==0){
				map[i]=needle.length();
			}
		}
		return map;
	}
	

	/**
	 * Run Boyer-Moore on the given strings, looking for needle in haystack.
	 * Return an array of the indices of the occurrence of the needle in the
	 * haystack. 
	 * 
	 * If there are matches that start at index 4, 7, and 9 in the
	 * haystack, return an array containing only 4, 7, and 9. If there are no
	 * matches return an empty array, new int[0]
	 * 
	 * Running time matters, you will not get full credit if it is not
	 * implemented correctly
	 * 
	 * 
	 */
	public static int[] boyerMoore(String needle, String haystack){
		int[] map=buildCharTable(needle);
		
		int shiftNeedle=needle.length()-1;
		
		ArrayList<Integer> result=new ArrayList<Integer>();
		
		int insideNeedle=needle.length()-1;
	
		while(shiftNeedle<haystack.length())
		{
			int count=needle.length()-1;
			while(count>=0&&compare(needle.charAt(count),haystack.charAt(insideNeedle)))
			{
				insideNeedle--;
				count--;
			}
			
			if(count<0){
				result.add(insideNeedle+1);
				shiftNeedle+=map[haystack.charAt(insideNeedle+1)];
				insideNeedle=shiftNeedle;
			}
			else{
				shiftNeedle+=map[haystack.charAt(insideNeedle)];
				insideNeedle=shiftNeedle;
			}
		}
		int[] resultArr=new int[result.size()];
		for(int i=0;i<result.size();i++){
			resultArr[i]=result.get(i);
		}
		
		
		return resultArr;
	}
	
	public static boolean compare(char a,char b){
		if(a==b){
			return true;
		}
		else{
			return false;
		}
	}

	public static int max(int a, int b)
	{
		if (a > b)
		{
			return a;
		}
		return b;
	}
	/**
	 * Return a table for use with KMP. In this table, table[i] is the length of
	 * the longest possible prefix that matches a proper suffix in the string
	 * needle.substring(0, i)
	 */
	public static int[] buildTable(String needle) {
		int[] table = new int[needle.length()];
		
		int position = 2, count = 0;
		table[0] = -1;
		table[1] = 0;
		while (position < needle.length())
		{
			if (needle.charAt(count) == needle.charAt(position-1))
			{
				count++;
				table[position] = count;
				position++;
			}
			else if (count > 0)
			{
				count = table[count];
			}
			else
			{
				table[position] = 0;
				position++;
			}
		}
		
		return table;
	}

	/**
	 * Run Knuth-Morris-Pratt on the given strings, looking for needle in
	 * haystack. Return an array of the indices of the occurrence of the needle
	 * in the haystack.
	 * 
	 * If there are matches that start at index 4, 7, and 9 in the
	 * haystack, return an array containing only 4, 7, and 9. If there are no
	 * matches return an empty array, new int[0]
	 */
	public static int[] kmp(String needle, String haystack) {
		int i = 0, m = 0;
		int[] table = buildTable(needle);
		ArrayList<Integer> result=new ArrayList<Integer>();
		while (m+i < haystack.length())
		{
			if (needle.charAt(i) == haystack.charAt(m+i))
			{
				if (i == needle.length()-1)
				{
					result.add(m);
					m=m+i-table[i];
					if (table[i] > -1)
					{
						i = table[i];
					}
					else
					{
						i = 0;
					}
				}
				i++;
			}
			else
			{
				m=m+i-table[i];
				if (table[i] > -1)
				{
					i = table[i];
				}
				else
				{
					i = 0;
				}
			}
			
		}
		int[] resultArr=new int[result.size()];
		for(int k=0;k<result.size();k++){
			resultArr[k]=result.get(k);
		}
		return resultArr;
	}
	
	// This is the base you should use, don't change it
	public static final int BASE = 1332;

	/**
	 * Given the hash for a string, return the hash for that string removing
	 * oldChar from the front and adding newChar to the end.
	 * 
	 * Power is BASE raised to the power of the length of the needle
	 */
	public static int updateHash(int oldHash, int power, char newChar,
			char oldChar){
		return (oldHash-oldChar*power)*BASE+newChar;
	}

	
	/**
	 * Hash the given string, using the formula given in the homework
	 */
	public static int hash(String s) {
		int hashresult=0;
		for(int i=0;i<s.length();i++){
			char cha=s.charAt(i);
			int added=cha*(int)Math.pow(BASE,s.length()-1-i);
			hashresult+=added;
		}
		return hashresult;
	}

	/**
	 * Run Rabin-Karp on the given strings, looki"ng for needle in haystack.
	 * Return an array of the indices of the occurrence of the needle in the
	 * haystack.
	 * 
	 * If there are matches that start at index 4, 7, and 9 in the
	 * haystack, return an array containing only 4, 7, and 9. If there are no
	 * matches return an empty array, new int[0]
	 */
	public static int[] rabinKarp(String needle, String haystack) {
		int needleH=hash(needle);
		int hayStackH=hash(haystack.substring(0, needle.length()));
		ArrayList<Integer> result=new ArrayList<Integer>();
		
		for(int i=0;i<haystack.length()-needle.length()+1;i++){
			if(needleH==hayStackH){
				result.add(i);
			}
			if(i!=haystack.length()-needle.length()){
				hayStackH=updateHash(hayStackH,(int)Math.pow(BASE,needle.length()-1),
						haystack.charAt(i+needle.length()),haystack.charAt(i));
			}
		}
		
		
		int[] resultArr=new int[result.size()];
		for(int i=0;i<result.size();i++){
			resultArr[i]=result.get(i);
		}
		
		return resultArr;
	}
}

