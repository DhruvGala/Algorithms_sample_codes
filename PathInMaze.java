/*
 * filename: SortCards.java
 * 
 * Version : 1.0 Date : 03-19-2015
 * 
 * Revisions : $Log Initial version$
 * 
 */
import java.util.Scanner;



/**
 * The following code searches for a path (in least moves possible) from a source to a
 * destination in the given m*n maze represented by a matrix of integers, in which 1 
 * represents as the wall while 0 represents as empty space and a valid move. The source 
 * here is integer 2 while the destination is integer 3 (only one of each).
 *     
 * @author DhruvGala
 *
 * To implement the given problem I have used the BFS algorithm using Queue as the data
 * structure.
 * 
 * sample Input/Output:
 * 5 6
 * 1 1 0 0 1 0
 * 1 2 0 0 0 0
 * 0 1 1 1 0 0
 * 0 0 0 1 1 0
 * 3 1 0 0 0 0
 * 
 * 14
 */
class Queue{

	Point head,tail;
	
	public Queue() {
		head = tail = null;
	}
	
	
	/**
	 * This method enqueues the passed variable in the queue. 
	 * 
	 * @param x
	 * @param y
	 */
	public void enqueue(int x,int y){
		Point addThis = new Point(x,y);
		if(isEmpty()){
			head = addThis;
			tail = addThis;
		}
		else{
			tail.next = addThis;
			tail = addThis;
			tail.next = null;
		}
	}
	
	
	/**
	 * The following method is used to dequeue an element.
	 * 
	 * @return
	 */
	public Point dequeue(){
		Point returnThis = head;
		if(head.next == null){
			tail = null;
		}
		else{
			head = head.next;
		}
		return returnThis;
	}
	
	
	/**
	 * This method checks if the queue is empty.
	 * 
	 * @return
	 */
	public boolean isEmpty(){
		if(this.tail == null)
			return true;
		return false;
	}
}


/**
 * This is the data structure used for this program. It stores the x and y co-ordinate
 * of the maze matrix.
 * 
 * @author DhruvGala
 *
 */
class Point{
	Point next;
	int x,y;
	
	
	/**
	 * Constructor of the data structure.
	 * 
	 * @param x
	 * @param y
	 */
	Point(int x,int y){
		this.x = x;
		this.y = y;
		next=null;
	}
}


/**
 * This class carries out the actual computation process.
 * 
 * @author DhruvGala
 *
 */
public class PathInMaze {
	
	static int m,n;
	static int[][] maze;
	static boolean[][] seen;
	static int[][] movesToReach;
	public static Queue Q = new Queue();
	
	
	
	/**
	 * 
	 */
	static void feedInput(){
		Scanner takeInput = new Scanner(System.in);
		m = takeInput.nextInt();
		n = takeInput.nextInt();
		maze = new int[m][n];
		seen = new boolean[m][n];
		
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				maze[i][j] = takeInput.nextInt();
				seen[i][j] = false;
			}
		}
		takeInput.close();
	}
	
	
	
	/**
	 * The following method gets the source point.
	 * 
	 * @return
	 */
	static Point getStartingPoint(){
		Point s = new Point(-1,-1);
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(maze[i][j] == 2){
					s = new Point(i,j);
				}
			}
		}
		return s;
	}
	
	
	
	/**
	 * This method computes the path using BFS algorithm.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	static int getPath(int x, int y){
		Q.enqueue(x, y);
		movesToReach = new int[m][n];
		
		while(!Q.isEmpty()){
			Point P = Q.dequeue();
			seen[P.x][P.y] = true;
			
			if(reachedHouse(P)){
				return movesToReach[P.x][P.y];
			}
			
			if(isValidMove(P.x+1,P.y)){
				seen[P.x+1][P.y] = true;
				Q.enqueue(P.x+1, P.y);
				movesToReach[P.x+1][P.y] = movesToReach[P.x][P.y] + 1;
				
				//System.out.println(P.x+","+P.y+"->"+(P.x+1)+","+P.y);
			}
			
			if(isValidMove(P.x,P.y+1)){
				seen[P.x][P.y+1] = true;
				Q.enqueue(P.x, P.y+1);
				movesToReach[P.x][P.y+1] = movesToReach[P.x][P.y] + 1;
				
				//System.out.println(P.x+","+P.y+"->"+P.x+","+(P.y+1));
			}
			
			if(isValidMove(P.x-1,P.y)){
				seen[P.x-1][P.y] = true;
				Q.enqueue(P.x-1, P.y);
				movesToReach[P.x-1][P.y] = movesToReach[P.x][P.y] + 1;
				
				//System.out.println(P.x+","+P.y+"->"+(P.x-1)+","+P.y);
			}
			
			if(isValidMove(P.x,P.y-1)){
				seen[P.x][P.y-1] = true;
				Q.enqueue(P.x, P.y-1);
				movesToReach[P.x][P.y-1] = movesToReach[P.x][P.y] + 1;
				
				//System.out.println(P.x+","+P.y+"->"+P.x+","+(P.y-1));
			}
		}
		
		return -1;
	}
	
	
	
	/**
	 * this method checks if the passed point represents a valid move.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	static boolean isValidMove(int x, int y){
		return ((x >= 0 && x < m) && (y >= 0 && y < n) && 
				(maze[x][y] != 1) && (maze[x][y] == 0 || 
				maze[x][y] == 3) && !seen[x][y]);
	}
	
	
	
	/**
	 * This method checks if the destination is reached.
	 * 
	 * @param P
	 * @return
	 */
	static boolean reachedHouse(Point P){
		return (maze[P.x][P.y] == 3);
	}
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		feedInput();
		Point s = getStartingPoint();
		System.out.println(getPath(s.x, s.y));
	}

}
