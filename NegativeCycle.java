/*
 * filename: NegativeCycle.java
 * 
 * Version : 1.0 Date : 04-20-2015
 * 
 * Revisions : $Log Initial version$
 * 
 */
import java.util.Scanner;


/**
 * The following code is to determine if a negative cycle exist in the given graph
 * of weighted (even negative weights) edges.
 * 
 * @author DhruvGala
 * 
 * Sample INput/Output:
 * 10 21
 * 1 5 520
 * 1 8 539
 * 1 10 413
 * 2 5 90
 * 2 8 913
 * 3 8 313
 * 3 9 580
 * 4 1 612
 * 4 2 -1370
 * 5 2 828
 * 5 10 593
 * 6 4 92
 * 6 7 75
 * 6 8 615
 * 6 9 125
 * 7 3 12
 * 8 1 17
 * 9 7 1
 * 9 8 812
 * 10 9 220
 * 1 4 123
 * 
 * YES
 */
class MyGraphStructure{
	MyEdge top,bottom;
	int size;
	
	/**
	 * The following is the data structure of the graph.
	 * 
	 */
	MyGraphStructure(){
		top = bottom = null;
		size = 0;
	}
	
	
	/**
	 * This method adds the edge to the graph.
	 * 
	 * @param to
	 * @param weight
	 */
	public void addEdge(int to,int weight){
		MyEdge addThis = new MyEdge( to, weight);
		if(top == null){
			top = addThis;
			bottom = top;
		}
		else{
			bottom.next = addThis;
			bottom = addThis;
		}
		size++;
	}
	
	
	/**
	 * This method returns all the edges from a point.
	 * 
	 * @return
	 */
	int[][] getAllEdgesOf(){
		MyEdge travel = this.top;
		int setOfVs[][] = new int[2][this.size],i = 0;
		while(travel != null){
			setOfVs[0][i] = travel.to;
			setOfVs[1][i++] = travel.weight;
			travel = travel.next;
		}
		return setOfVs;
	}
	
	
	
	/**
	 * This method was implemented just fo debuggin the code. It basically displays
	 * the entire graph.
	 * 
	 */
	public void display(){
		MyEdge travel = this.top;
		while(travel != null){
			System.out.print(travel.to+" costs "+travel.weight);
			travel = travel.next;
			if(travel != null){
				System.out.print(",->");
			}
		}
		System.out.println();
	}
}



/**
 * This is the building block of the data structure of the graph.
 * 
 * @author DhruvGala
 *
 */
class MyEdge{
	MyEdge next;
	int to,weight;
	
	
	/**
	 * A constructor of the edge data structure.
	 * 
	 * @param to
	 * @param weight
	 */
	MyEdge( int to, int weight){
		//this.from = from;
		this.to = to;
		this.weight = weight;
		next = null;
	}
}



/**
 * This class contains the entire logic of detecting the negative cycle.
 * Initially I detect if there exist any cycle in the graph by detecting
 * the strongly connected components in the graph. Then find the total sum
 * of weights of all the edges in that all the strongly connected components.
 * If the sum is non-positive then the graph contains a negative cycle.
 * 
 * @author DhruvGala
 *
 */
public class NegativeCycle {

	
	static int m,n,u,v,negWt;	
	static MyGraphStructure[] graph;
	
	
	/**
	 * 
	 */
	static void feedInput(){
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		graph = new MyGraphStructure[n];
		for (int j = 0; j < n; j++) {
			graph[j] = new MyGraphStructure(); 
		}
		int from = 0,to = 0,wt = 0;
		for(int i=0;i<m;i++){
			from = sc.nextInt();
			to = sc.nextInt();
			wt = sc.nextInt();
			if(wt < 0){//remember where -ve wt encountered
				u = from;
				v = to;
				negWt = wt;
			}
			graph[from-1].addEdge(to, wt);
		}
		
		sc.close();
	}
	
	
	/**
	 * The method to display the graph. Used for debugging.
	 * 
	 */
	static void whatsInTheGraph(){
		for(int i=0;i<n;i++){
			System.out.print((i+1)+"->");
			graph[i].display();
		}
	}
	
	
	/**
	 * The following method of BFS is used to traverse the given graph.
	 * 
	 * @return
	 */
	static String applyBFS(){
		int[] dist = new int[n];
		int[] Q = new int[n];
		boolean[] seen = new boolean[n];
		//boolean flag = false;
		for(int i=0;i<n;i++){
			seen[i] = false;
			//dist[i] = Integer.MAX_VALUE;
		}
		int beg = 0,end = 1;
		Q[0] = v-1;
		seen[v-1] = true;
//		dist[v] = 0;
		
		
		while(beg<end){
			int head = Q[beg];
			int[][] neighbors = graph[head].getAllEdgesOf();
			for(int j=0;j<neighbors[0].length;j++){
				if(!seen[neighbors[0][j]-1]){
					Q[end] = neighbors[0][j]-1;
					dist[neighbors[0][j]-1] = dist[head] + neighbors[1][j];
					seen[neighbors[0][j]-1] = true;
					end++;
				}
				if(neighbors[0][j]==u ){
					if((dist[neighbors[0][j]-1]+negWt<0)){
						return "YES";
					}
					else{
						seen[neighbors[0][j]-1] = false;
						end--;
					}
				}
			}
			beg++;
		}
		return "NO";
	}
	
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		feedInput();
		//whatsInTheGraph();
		System.out.println(applyBFS());
	}
}
