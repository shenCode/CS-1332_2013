import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class DisjointSets<T> {
	public HashMap hashM;
	public Node<T>[] Nodes;
	public Object[] vertexs;
	/**
	 * @param setItems
	 *            the initial items (sameSet and merge will never be called with
	 *            items not in this set, this set will never contain null
	 *            elements)
	 */
	public DisjointSets(Set<T> setItems) {
		// TODO
		Nodes=new Node[setItems.size()];
		vertexs=new Vertex[setItems.size()];
		hashM=new HashMap();
		vertexs=setItems.toArray();
		for (int i = 0; i < setItems.size(); i++)
		{
			Node<T> temp = new Node(vertexs[i]);
			Nodes[i]=temp;
			hashM.put(temp.data.hashCode(),temp.data);
		}
	}
	
	
	private class Node<T>{
		public T data;
		public Node<T> parent=null;
		public Node(T data){
			this.data=data;
		}
		public Node<T> getParent(){
			return parent;
		}
		public void setParent(Node<T> parent){
			this.parent=parent;
		}
	}
	
	
	public Node<T> find(T u)
	{
		for (int i = 0; i <vertexs.length; i++)
		{
			if (Nodes[i].data.equals(u))
			{
				return Nodes[i];
			}
		}
		return null;
	}

	
	
	
	/**
	 * @param u
	 * @param v
	 * @return true if the items given are in the same set, false otherwise
	 */
	public boolean sameSet(T u, T v){
		// TODO
		Node<T> temp1=find(u);
		Node<T> temp2=find(v);
		if (findRoot(temp1) == findRoot(temp2))
		{
			return true;
		}
		return false;

	}
	
	
	public Node<T> findRoot(Node<T> u)
	{
		while (u.getParent() != null)
		{
			u=u.getParent();
		}
		return u;
	}
	
	
	
	
	/**
	 * merges the sets u and v are in, do nothing if they are in the same set
	 * 
	 * @param u
	 * @param v
	 */
	public void merge(T u, T v) {
		// TODO
			Node<T> temp1=find(u);
			Node<T> temp2=find(v);
			while(temp1.getParent()!=null){
				temp1=temp1.getParent();
			}
			temp1.setParent(temp2);
	}
}

