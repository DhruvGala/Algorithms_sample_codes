/*
 * filename: MyWeightedGraph.java
 * 
 * Version : 1.0 Date : 04-20-2015
 * 
 * Revisions : $Log Initial version$
 * 
 */

/**
 * The following java code is the underlying data structure to store and manipulate 
 * the data in form of a graph.
 * 
 * @author DhruvGala
 *
 */
class MyEdge{
	int weight,u,v;
	MyEdge next;
	
	
	/**
	 * The following code is the constructor of edge data structure.
	 * 
	 * @param weight
	 * @param u
	 * @param v
	 */
	MyEdge(int weight,int u,int v){
		this.u = u;
		this.v = v;
		this.weight = weight;
		next = null;
	}
	
}


/**
 * The actual data structure of weighted graph is implemented as follows. It utilizes 
 * the edge as the building block of the graph.
 * 
 * @author DhruvGala
 *
 */
public class MyWeightedGraph {

	int noOfEdges;
	MyEdge root,currentEdge;
	
	
	/**
	 * 
	 */
	MyWeightedGraph(){
		noOfEdges = 0;
		root = currentEdge = null;
	}
	
	
	
	/**
	 * This method is used to enter a new edge to the graph.
	 * 
	 * @param wt
	 * @param u
	 * @param v
	 */
	public void addThisToGraph(int wt,int u,int v){
		MyEdge addThis = new MyEdge(wt,u,v);
		if(root == null){
			root = addThis;
			currentEdge = root;
		}
		else{
			currentEdge.next = addThis;
			currentEdge = addThis;
		}
		noOfEdges++;
	}
	
	
	
	/**
	 * The following method is used pull info regarding a particular edge.
	 * 
	 * @param thisEdge
	 * @return
	 */
	public int[] getEdge(MyEdge thisEdge){
		int[] Edgedetails = new int[3];
		Edgedetails[0]=thisEdge.weight;
		Edgedetails[1]=thisEdge.u;
		Edgedetails[2]=thisEdge.v;
		
		return Edgedetails;
	}
	
	
	/**
	 * This is a demo method implemented for the sole purpose of debugging.
	 * It displays the entire content of the graph.
	 * 
	 */
	public void display(){
		MyEdge temp = root;
		while(temp != null){
			System.out.println(temp.weight+" "+temp.u+"-"+temp.v);
			temp = temp.next;
		}
	}
}
