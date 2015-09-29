/*
 * filename: HowManyMST.java
 * 
 * Version : 1.0 Date : 04-20-2015
 * 
 * Revisions : $Log Initial version$
 * 
 */
import java.util.Scanner;

/**
 * The following code determines how many Minimum Spanning Trees (MSTs) are possible
 * from the given graph.
 * I have implemented another data structure code named, "MyWeightedGraph.java" which
 * is used to store and process the weighted graph.
 * The data structure of union-find is also used to determine the MSTs in O(m log n).
 * 
 * @author DhruvGala
 * 
 * Sample Input/Output:
 * 6 11
 * 0 1 3
 * 0 3 2
 * 0 2 1
 * 1 2 5
 * 1 3 3
 * 2 3 2
 * 2 5 1
 * 2 4 4
 * 3 4 5
 * 3 5 6
 * 4 5 4
 * 
 * 8
 */

class UnionFind{
	int[] boss,size; 
	MyLinkedListForSets[] set;
	int n = HowManyMST.n;
	
	
	/**
	 * The following constructor initializes the Union-Find data structure.
	 * 
	 */
	UnionFind(){
		initialize();
	}
	
	
	
	/**
	 * This method is called to initialize the Union-Find data structure.
	 * 
	 */
	public void initialize(){
		boss = new int[n];
		size = new int[n];
		set = new MyLinkedListForSets[n];
		for(int i=0;i<n;i++){
			boss[i] = i;
			size[i] = 1;
			set[i] = new MyLinkedListForSets();
			set[i].addThisToSet(i); 
		}
	}
	
	
	
	/**
	 * The following method determines if a cycle exist in the possible path due to inclusion
	 * of a new point (here v), by comparing the boss of both the points.
	 * 
	 * @param u
	 * @param v
	 * @return
	 */
	public boolean thereAintCycle(int u, int v){
		return !(boss[u]==boss[v]);
	}
	
	
	
	
	/**
	 * This method is used to perform the union operation which plays the key role in
	 * maintaining the time complexity to O(m log n).
	 * 
	 * @param u
	 * @param v
	 */
	public void performUnion(int u, int v){
		if(size[boss[u]] <= size[boss[v]]){
			int temp = u; u=v; v= temp;
		}
		set[boss[u]] = set[boss[u]].performUnioinWith(set[boss[v]]);
		size[boss[u]] += size[boss[v]];
		int[] elementsOfV = set[boss[v]].giveMyElements();
		for(int z=0;z<elementsOfV.length;z++){
			boss[elementsOfV[z]] = boss[u];
		}
		
	}
}


/**
 * The following code is the actual logic of determining the number of MSTs possible
 * for a given graph.
 * 
 * @author DhruvGala
 *
 */
public class HowManyMST {

	
	static int n,m;
	static int[] fromVertex,toVertex,cost;
	static int[][] graph;
	static MyWeightedGraph sortedGraph,myMST;
	
	
	/**
	 * Takes input from the user.
	 */
	static void feedInput(){
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		
		fromVertex = new int[m];
		toVertex = new int[m];
		cost = new int[m];
		
		for(int i=0;i<m;i++){
			fromVertex[i] = scan.nextInt();
			toVertex[i] = scan.nextInt();
			cost[i] = scan.nextInt();
		}
		scan.close();
		
		graph = new int[3][m];
		
		for(int i=0;i<m;i++){
			graph[0][i] = cost[i];
			graph[1][i] = fromVertex[i];
			graph[2][i] = toVertex[i];
		}
		
	}
	
	
	
	/**
	 * The following method sorts the graph point according to the cost of the edges.
	 * 
	 */
	public static void makeSortedGraph(){
		sortedGraph = new MyWeightedGraph();
		for(int i=0;i<m;i++){
			int wt = graph[0][i];
			int u = graph[1][i];
			int v = graph[2][i];
			sortedGraph.addThisToGraph(wt, u, v);
			//System.out.println(wt+" "+u+" "+v);
		}
		
	}
	
	
	
	/**
	 * I used the logic of Kruskal's algorithm to determine if there exist a MST.
	 * The simple logic applied here is if there are two choices when choosing
	 * a weighted edge then 2 time possible combinations of MSTs is possible if and
	 * only if the other choice don't make up in the initial MST. If both the choices
	 * are valid to be choose one after another then the possible combinations get 
	 * again merged to half.
	 * 
	 */
	public static void applyKruskal(){
		
		UnionFind forMST = new UnionFind();
		MyEdge travelGraph = sortedGraph.root;
		int iteration = 1,combinations = 1;
		int lastEdgeWt = 0;
		
		while(iteration<n){
			boolean flag = false;
			//System.out.println("------interation "+iteration+"------"+lastEdgeWt);
			int[] edge = sortedGraph.getEdge(travelGraph);
			int[] nextEdge = sortedGraph.getEdge(travelGraph.next);
			//System.out.println("edge now:"+edge[0]+" "+edge[1]+"-"+edge[2]);
			
			if(forMST.thereAintCycle(edge[1], edge[2])){
				if(edge[0] == nextEdge[0]){
					
					boolean firstChoice = forMST.thereAintCycle(edge[1], edge[2]);
					boolean secondChoice = forMST.thereAintCycle(nextEdge[1], nextEdge[2]);
					
					if (firstChoice && secondChoice) {
						//System.out.println("combo-->"+edge[0]);
						combinations *= 2;
					}
					
					if(firstChoice){
						forMST.performUnion(edge[1], edge[2]);
						//System.out.println("edge added-->"+edge[1]+"-"+edge[2]);
						flag = true;
					}
					
				}
				else{
					if(lastEdgeWt == edge[0]){
						//System.out.println("-->converge<--");
						combinations /= 2;
					}
					//combinations *= 1;
					
					forMST.performUnion(edge[1], edge[2]);
					//System.out.println("edge added-->"+edge[1]+"-"+edge[2]);
					flag = true;
				}
				
				iteration++;
			}
			if(flag){
				lastEdgeWt = edge[0]; 
			}
			travelGraph = travelGraph.next;
		}
		
		System.out.println(combinations);
	}
	
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		feedInput();
		MyMergeSortForSortingAccordingToWeights ms = new MyMergeSortForSortingAccordingToWeights();
		graph = ms.mergeSort(graph, m);
		
		makeSortedGraph();
		//sortedGraph.display();
		applyKruskal();
	}
}
