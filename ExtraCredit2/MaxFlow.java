import java.util.Queue;
import java.util.LinkedList;
public class MaxFlow {

	/**
	 * We will be grading this method solely based on the values you return. Yes
	 * it will be strict, but this is extra credit. There is only 1 method so we
	 * obviously won't be testing the intermediate parts of the algorithm. We
	 * are giving you the freedom to code it however you want though.
	 * 
	 * (the same things about changing the method headers and class headers from
	 * all previous homeworks applies to this, I just didn't feel the need to
	 * attach an entirely separate pdf just containing it. reference an old
	 * homework if you forgot what it says!)
	 * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * 
	 * This method should return a single integer, the maximum flow from s to t.
	 * You can assume that the max flow exists and is greater than 0.
	 * 
	 * There are n vertices in the graph. Numbered 0 to n-1 (inclusive).
	 * 
	 * capacities will be a n by n matrix, that represents an adjacency matrix
	 * of the network. capacities[i][j] is the capacity that can flow from
	 * vertex i to vertex j. A 0 represents there being no edge between i and j
	 * in the network.
	 * 
	 * note that the graph is directed, you may assume that if capacities[i][j]
	 * is greater than 0, then capacities[j][i] is 0, in other words there will
	 * be only one edge between two vertices in either direction
	 * 
	 * s is the source vertex that the flow should start from, s will be in the
	 * range [0, n)
	 * 
	 * t is the sink where the flow should end at, t will be in the range [0, n)
	 * 
	 * @param n
	 *            the number of vertices, this will be less than 500
	 * @param s
	 *            the source vertex
	 * @param t
	 *            the sink vertex
	 * @param capacities
	 *            the capacities of the network
	 * @return the maximum flow of the network
	 */
	private static int capacity[][];
	private static int flow[][];
	private static boolean visited[];
	private static int pre[];
	private static int nodes;
	
	public static int flow(int n, int s, int t, int[][] capacities) {
		capacity=capacities;
		nodes=n;
		flow=new int[nodes][nodes];
		pre=new int[nodes];
		visited=new boolean[nodes];
		return maxFlow(s,t);
	}
	public static int maxFlow(int src, int des)
	{
		int maxFlow=0;
		for(int i=0;i<nodes;i++)
		{
			for(int j=0;j<nodes;j++)
			{
				flow[i][j]=0;
			}
		}
		
		while(true)
		{
			for(int i=0;i<nodes;i++)
			{
				visited[i]=false;
			}
			pre[src]=-1;
			if (!BFS(src,des))
			{
				break;
			}
			int increment=100000000;
			for (int i=des;pre[i]>=src;i=pre[i])
			{
				increment=Math.min(increment,capacity[pre[i]][i]-flow[pre[i]][i]);
			}
			for (int i=des;pre[i]>=src;i=pre[i])
			{
				flow[pre[i]][i]+=increment;
				flow[i][pre[i]]-=increment;
			}
			maxFlow+=increment;
		}
		
		return maxFlow;
	}
	
	private static boolean BFS(int src,int des)
	{
		Queue<Integer> queue=new LinkedList<Integer>();
		queue.add(src);
		visited[src]=true;
		while (!queue.isEmpty())
		{
			int node=queue.poll();
			for(int i=0;i<nodes;i++){
				if(!visited[i]&&(capacity[node][i]-flow[node][i]>0))
				{
					queue.add(i);
					visited[i]=true;
					pre[i]=node;
				}
			}
		}
		
		return visited[des];
	}
	

}
