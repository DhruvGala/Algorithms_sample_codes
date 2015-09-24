import java.util.Scanner;


/**
 * The following code is the direct application of longest common subsequence. The rules
 * of replacing String X with Y are as follows:
 * (1) at a cost of 3, you can delete a single character from X.
 * (2) at a cost of 4, you can insert a single character at any position in X.
 * (3) at a cost of 5, you can replace two consecutive characters in X with some other
 * character.
 * 
 * @author DhruvGala
 *
 * sample Input/Output:
 * beard
 * bored
 * 
 * 9
 */
public class StringConvert {

	static String X,Y;
	

	/**
	 * This method calculates the minimum possible cost function to evaluate the 
	 * the total cost of the function  to replace X with Y completely using the 
	 * dynamic programming.
	 * 
	 * @param X
	 * @param Y
	 * @return
	 */
	static int calculateDistance(String X,String Y){
		int M=X.length();
		int N=Y.length();
		int[][] cost= new int [M+1][N+1];
		
		for(int i=1;i<=M;i++){
			cost[i][0] = cost[i-1][0]+3;
		}
		
		for(int j=1;j<=N;j++){
			cost[0][j] = cost[0][j-1]+4;
		}
		
		
		for(int i=1;i<=M;i++){
			for(int j=1;j<=N;j++){
				cost[i][j] = minimumOf((cost[i-1][j]+3),cost[i][j-1]+4,whichCase(X,Y,i,j,cost));
			}
		}
		
		
		/**
		 * This is for debugging the cost table for dynamic programming.
		 * 
		 */
		/*System.out.println("COST-->");
		for(int i=0;i<=M;i++){
			for(int j=0;j<=N;j++){
				System.out.print(cost[i][j]+",");
			}
			System.out.println();
		}*/
		
		return cost[M][N];
	}
	
	
	/**
	 * The following method could be termed as the heart of the solution.
	 * the cost function is determined by the mathematical cases possible and the 
	 * same are computed in here.
	 * 
	 * @param X
	 * @param Y
	 * @param i
	 * @param j
	 * @param cost
	 * @return
	 */
	static int whichCase(String X, String Y,int i,int j,int cost[][]){
		
		int INFINITY = (int) (Math.pow(2, 32)-1);
		if(X.charAt(i-1) == Y.charAt(j-1)){
			return cost[i-1][j-1] + 0;               
		}
		else	
			if(i>=2){
				return cost[i-2][j-1] + 5;
			}
			else{
				return INFINITY;
			}
	}
	
	/**
	 * The foloowing method returns the minimum of all the three arguments that
	 * are supplied to the method.
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	static int minimumOf(int a,int b,int c){
		return (a<b)?(a<c?a:c):(b<c?b:c);
	}
	
	
	
	/**
	 * 
	 */
	static void feedInput(){
		Scanner takeInput = new Scanner(System.in);
		X = takeInput.nextLine();
		Y = takeInput.nextLine();
		takeInput.close();
	}
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		feedInput();
		System.out.println(calculateDistance(X,Y));
	}
}
