import java.util.ArrayList;
import java.util.Stack;

public class Dynamic implements DynamicInterface {

	@Override
	public int lcs(String a, String b) {
		
		String longWord = a, shortWord = b;
		int max = 0;
		if (a.length() < b.length())
		{longWord = b;
			shortWord = a;
		}int[][] table = new int[shortWord.length()][longWord.length()];

		// Grape, Sharpie
		for (int i = 0; i < shortWord.length(); i++)
		{
			for (int j = 0; j < longWord.length(); j++)
			{
				if (shortWord.charAt(i) == longWord.charAt(j))
				{
					if (i == 0 || j == 0)
					{
						table[i][j] = 1;
					}
					else {table[i][j] = table[i-1][j-1]+1;}
				}
				else
				{
					if (i == 0 || j == 0)
					{
						table[i][j] = 0;
					}
					else {table[i][j] = Math.max(table[i-1][j], table[i][j-1]);}
				}
				if (table[i][j] > max)
				{max = table[i][j];}
			}}
		
		for (int k = 0; k < shortWord.length(); k++)
		{
			for (int l = 0; l < longWord.length(); l++)
			{
				//System.out.print(table[k][l] + " ");
			}
			//System.out.println();
		}
		
		return max;
	}

	//--------------------------------------------------------------------------------------------------
	@Override
	public int edit(String a, String b) {
		int aCount = a.length()+1;
		int bCount = b.length()+1;
		int[][] map = new int[aCount][bCount];
		for (int i = 0; i < aCount; i++)
		{
			map[i][0] = i;
		}
		for (int i = 0; i < bCount; i++)
		{
			map[0][i] = i;
		}
		for (int i = 1; i < aCount; i++)
		{
			for (int j = 1; j < bCount; j++)
			{
				int diff = a.charAt(i-1) == b.charAt(j-1) ? 1 : 0;
				map[i][j] = Math.min(Math.min(map[i][j] + 1, diff + map[i-1][j-1]), Math.min(map[i][j-1] + 1, diff + map[i-1][j-1]));
			}
		}
		return map[aCount-1][bCount-1];
	}

	public int shop(int[] weight, int[] value, int max) {
		int[] table = new int[max+1];
		table[0] = 0;
		
		for (int i = 1; i <= max; i++)
		{
			table[i] = table[i-1];
			for (int j = 0; j < weight.length; j++)
			{
				if (i - weight[j] >= 0)
				{
					table[i] = Math.max(table[i], table[i - weight[j]] + value[j]);
				}
			}
		}
		return table[max];
	}

	@Override
	public long matrix(int[] matrices) {
		int l = matrices.length -1;
		long[][] table = new long[l][l];
		for (int i = 0; i < l; i++)
		{
			table[i][i] = 0;
		}
		for (int n = 1; n < l; n++)
		{
			for (int i = 0; i < l-n; i++)
			{
				int j = n + i;
				int k = i;
				table[i][j] = Integer.MAX_VALUE;
				for (k = i; k < j; k++)
				{
					long result = table[i][k] + table[k+1][j] + matrices[i]*matrices[j+1]*matrices[k+1];
					table[i][j] = result > table[i][j] ? table[i][j] : result;
				}
			}
		}
		return table[0][l-1];
	}

	@Override
	public int robbers(int[] weight, int[] value, int max) {
		int[][] table = new int[max+1][weight.length+1];

		for (int i = 0; i < max+1; i++)
		{
			table[i][0] = 0;
		}
		for (int i = 0; i < weight.length+1; i++)
		{
			table[0][i] = 0;
		}
		for (int j = 1; j <= weight.length; j++)
		{
			for (int w = 1; w <= max; w++)
			{
				if (weight[j-1] > w)
				{
					table[w][j] = table[w][j-1];
				}
				else
					table[w][j] = Math.max(table[w][j-1], table[w-weight[j-1]][j-1] + value[j-1]);
			}
		}
		return table[max][weight.length];
		
	}
	@Override
	public int circus(String[] forest) {
		int max = 0;
		int[][] woods = translate(forest);
		int[][] newForest = new int[woods.length][woods[0].length];
		for (int i = 0; i < newForest.length; i++)
		{
			if (woods[i][0] == 0)
			{
				newForest[i][0] = 1;
			}
			max = Math.max(max, newForest[i][0]);
		}
		
		for (int j = 0; j < newForest[0].length; j++)
		{
			if (newForest[0][j] == 0)
			{
				woods[0][j] = 1;
			}
			max = Math.max(max, newForest[0][j]);
		}
		
		for (int i = 1; i < newForest.length; i++)
		{
			for (int j = 1; j < newForest[0].length; j++)
			{
				if (woods[i][j] == 0)
				{
					newForest[i][j] = Math.min(newForest[i-1][j], newForest[i][j-1]);
					if (woods[i-newForest[i][j]][j-newForest[i][j]] == 0)
					{
						newForest[i][j]++;
					}
					max = Math.max(max, newForest[i][j]);
				}
			}
		}
		return max*max;
		
	}
	
	public int[][] translate(String[] forest)
	{
		int[][] woods = new int[forest.length][forest[0].length()];
		for (int i = 0; i < forest[0].length(); i++)
		{
			for (int j = 0; j < forest.length; j++)
			{
				if (forest[j].charAt(i) == '.')
				{
					woods[j][i] = 0;
				}
				else
				{
					woods[j][i] = 1;
				}
			}
		}
		
		for (int k = 0; k < woods.length; k++)
		{
			for (int l = 0; l < woods[0].length; l++)
			{
				System.out.print(woods[k][l]);
			}
			System.out.println();
		}
		return woods;
	}

	@Override
	public int[] realEstate(String[] flatLand) {
		return null;
	}
}