import java.util.Collection;
import java.util.HashSet;

public class MST {

	/**
	 * Run Kruskal's algorithm on the given graph and return the MST, return
	 * null if no MST exists for the graph
	 * 
	 * @param g
	 *            the graph, g will never be null
	 * @return the MST of the graph, null of no valid MST exists
	 */
	
	public static Collection<Edge> kruskals(Graph g) {
		Collection<Edge> result = new HashSet<Edge>(), edges = g.getEdgeList();
		DisjointSets<Vertex> vertex = new DisjointSets<Vertex>(g.getVertices());
		while (edges.size() >= 1)
		{
			Edge e = smallestEdge(edges);
			if (!vertex.sameSet(e.getU(), e.getV()))
			{
				result.add(e);
				vertex.merge(e.getU(), e.getV());
			}
		}
		
		return result;
	}
	
	public static Edge smallestEdge(Collection<Edge> e)
	{
		Edge res = e.iterator().next();
		for (Edge tmp : e)
		{
			if (tmp.getWeight() < res.getWeight())
			{
				res = tmp;
			}
		}
		e.remove(res);
		return res;
		
	}
}
