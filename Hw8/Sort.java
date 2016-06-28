import java.util.Random;


public class Sort {

	/**
	 * Implement bubble sort.
	 * 
	 * It should be:
	 *  inplace
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(n^2)
	 *  
	 * And a best case running time of:
	 *  O(n)
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<T>> void bubblesort(T[] arr) {
		if (arr.length == 1)
		{
			return;
		}
		// TODO Auto-generated method stub
		boolean done = false;
		while (!done)
		{
			done = true;
			for (int i = 0; i < arr.length - 1; i++)
			{
				if (arr[i].compareTo(arr[i+1]) > 0)
				{
					T tmp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = tmp;
					done = false;
				}
			}
		}
	}
	
	/**
	 * Implement insertion sort.
	 * 
	 * It should be:
	 *  inplace
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(n^2)
	 *  
	 * And a best case running time of:
	 *  O(n)
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<T>> void insertionsort(T[] arr) {
		if (arr.length == 1)
		{
			return;
		}
		for (int i = 1; i < arr.length; i++)
		{
			T curr = arr[i];
			int count = i;
			while (count > 0 && curr.compareTo(arr[count-1]) < 0)
			{
				arr[count] = arr[count-1];
				count--;
			}
			arr[count] = curr;
		}
	}
	
	/**
	 * Implement quick sort. 
	 * 
	 * Use the provided random object to select your pivots.
	 * For example if you need a pivot between a (inclusive)
	 * and b (exclusive) where b > a, use the following code:
	 * 
	 * int pivotIndex = r.nextInt(b - a) + a;
	 * 
	 * It should be:
	 *  inplace
	 *  
	 * Have a worst case running time of:
	 *  O(n^2)
	 *  
	 * And a best case running time of:
	 *  O(n log n)
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<T>> void quicksort(T[] arr, Random r) {
		if (arr.length == 1)
		{
			return;
		}
		quicksort(arr, 0, arr.length-1, r);
	}
	
	public static <T extends Comparable<T>> void quicksort(T[] arr, int front, int back, Random r)
	{
		if (front < back)
		{
			int pivotIndex = r.nextInt(back-front)+front;
			int pivotIndex2 = quick(arr, front, back, pivotIndex);
			quicksort(arr, front, pivotIndex2-1, r);
			quicksort(arr,pivotIndex2+1, back, r);
		}
	}
	
	public static <T extends Comparable<T>> int quick(T[] arr, int front, int behind,int i)
	{
		T p = arr[i];
		T tmp = arr[behind];
		arr[i] = tmp;
		arr[behind] = p;
		int count = front;
		for (int j = front; j < behind; j++)
		{
			if (arr[j].compareTo(p) < 0)
			{
				T tmp2 = arr[count];
				arr[count] = arr[j];
				arr[j] = tmp2;
				count++;
			}
		}
		T tmp3 = arr[count];
		arr[count] = arr[behind];
		arr[behind] = tmp3;
		return count;
	}
	
	/**
	 * Implement merge sort.
	 * 
	 * It should be:
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(n log n)
	 *  
	 * And a best case running time of:
	 *  O(n log n)
	 *  
	 * @param arr
	 * @return
	 */
	public static <T extends Comparable<T>> T[] mergesort(T[] arr) {
		if (arr.length == 1)
		{
			return arr;
		}
		mergesort(arr, 0, arr.length-1);
		return arr;
	}
	
	public static <T extends Comparable<T>> void mergesort(T[] arr, int start, int end)
	{
		if (start < end)
		{
			int mid = (start+end)/2;
			mergesort(arr, start, mid);
			mergesort(arr, mid+1, end);
			merge(arr, start, mid, end);
		}
	}
	
	public static <T extends Comparable<T>> void merge(T[] arr, int start, int mid, int end)
	{
		T[] tmp = (T[])(new Comparable[arr.length]);
		int m = mid+1;
		int tmpPointer = start;
		int a = start;
		while (start <=mid && m <= end)
		{
			if (arr[start].compareTo(arr[m])<= 0)
			{
				tmp[tmpPointer++]=arr[start++];
			}
			else
			{
				tmp[tmpPointer++]=arr[m++];
			}
		}
		
		while (m <= end)
		{
			tmp[tmpPointer++]=arr[m++];
		}
		while (start <= mid)
		{
			tmp[tmpPointer++]=arr[start++];
		}
		while (a <= end)
		{
			arr[a] = tmp[a++];
		}
	}
	
	/**
	 * Implement radix sort
	 * 
	 * Hint: You can use Integer.toString to get a string
	 * of the digits. Don't forget to account for negative
	 * integers, they will have a '-' at the front of the
	 * string.
	 * 
	 * It should be:
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(kn)
	 *  
	 * And a best case running time of:
	 *  O(kn)
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] radixsort(int[] arr) {
		final int BASE = 10;
		if (arr.length == 1)
		{
			return arr;
		}
		int size = arr.length, maxCount = 0, max=findMax(arr), d=1;
		int[][] tmp = new int[BASE][size];
		int[] tmpSize = new int[BASE], res = new int[size];
		
		while (max != 0)
		{
			max = max/BASE;
			maxCount++;
		}
		
		for (int i = 0; i < maxCount; i++)
		{
			for (int j = 0; j < arr.length; j++)
			{
				if (arr[j]/d%BASE == 0)
				{
					tmp[0][tmpSize[0]]=arr[j];
					tmpSize[0]++;
				}
				if (arr[j]/d%BASE == 1)
				{
					tmp[1][tmpSize[1]]=arr[j];
					tmpSize[1]++;
				}
				if (arr[j]/d%BASE == 2)
				{
					tmp[2][tmpSize[2]]=arr[j];
					tmpSize[2]++;
				}
				if (arr[j]/d%BASE == 3)
				{
					tmp[3][tmpSize[3]]=arr[j];
					tmpSize[3]++;
				}
				if (arr[j]/d%BASE == 4)
				{
					tmp[4][tmpSize[4]]=arr[j];
					tmpSize[4]++;
				}
				if (arr[j]/d%BASE == 5)
				{
					tmp[5][tmpSize[5]]=arr[j];
					tmpSize[5]++;
				}
				if (arr[j]/d%BASE == 6)
				{
					tmp[6][tmpSize[6]]=arr[j];
					tmpSize[6]++;
				}
				if (arr[j]/d%BASE == 7)
				{
					tmp[7][tmpSize[7]]=arr[j];
					tmpSize[7]++;
				}
				if (arr[j]/d%BASE == 8)
				{
					tmp[8][tmpSize[8]]=arr[j];
					tmpSize[8]++;
				}
				if (arr[j]/d%BASE == 9)
				{
					tmp[9][tmpSize[9]]=arr[j];
					tmpSize[9]++;
				}
			}
			
			int m, j, k;
			m = j = k = 0;
			while (k < arr.length)
			{
				if (j < tmpSize[m])
				{
					res[k]=tmp[m][j];
					j++;k++;
				}
				else
				{
					j=0;m++;
				}
			}
			d*=BASE;
			arr=res;
			tmp=new int[BASE][arr.length];
			tmpSize=new int[BASE];
		}
		return res;		
	}
	
	private static int findMax(int[] arr)
	{
		int tmp = arr[0];
		for (int i = 0; i < arr.length; i++)
		{
			if (arr[i] > tmp)
			{
				tmp = arr[i];
			}
		}
		return tmp;
	}
}
