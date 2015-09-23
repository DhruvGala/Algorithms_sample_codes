/**
 * Jobs.java
 * 
 * @ Version: 
 *     $Id: Jobs.java , Version 1.0 03/17/2015 $ 
 * 
 * Revisions: 
 *     $Log Initial Version $ 
 */

import java.util.Scanner;
/**
 * This program computes that maximum number of intervals that need to be scheduled
 * by using the classical algorithm of interval scheduling algorithm.
 * 
 * @author Dhruv Gala
 *
 * Sample Input/Output:
 * 8
 * 1 4 0
 * 2 8 1
 * 3 5 1
 * 10 12 1
 * 2 7 0
 * 4 8 0
 * 4 6 1
 * 8 9 0
 * 
 * 4
 */

class myMergeSort1{
	
	/**
	 * This method sorts the schedule according to finish timings.
	 * 	
	 * @param X
	 * @param n
	 * @return
	 */
	public int[][] mergeSort(int X[][], int n){
		
		if(n == 1){
			return X;
		}
		int middle = n/2,variable;
		
		if(n%2 == 0){
			variable = middle;
		}
		else{
			variable = middle+1;
		}
		
		int A[][]= new int[3][middle];
		int B[][]= new int[3][variable];
		
		for(int j=0;j<3;j++){
			for(int i=0;i<middle;i++){
				A[j][i]=X[j][i];
			}
		}
		
		for(int j=0;j<3;j++){
			for(int i=middle;i<n;i++){
				B[j][i-middle]=X[j][i];
			}
		}
		
		int As[][]=mergeSort(A, middle);
		int Bs[][]=mergeSort(B, n-middle);
		
		return merge(As,Bs);
	}
	
	
	/**
	 * This method actually merges the small pieces to one sorted sequence.
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public int[][] merge(int[][] A,int[][] B){
		int i=0,j=0,k=0;
		int m = A[0].length;
		int n = B[0].length;
		int C[][] = new int[3][m+n];
		
		while(k <= (m+n) && i<m && j<n ){
			if(A[0][i]<B[0][j]){
				C[2][k] = A[2][i];
				C[1][k] = A[1][i];
				C[0][k++] = A[0][i++];
			}
			else{
				C[2][k] = B[2][j];
				C[1][k] = B[1][j];
				C[0][k++] = B[0][j++];
			}
		}
		
		while(i != m){
			C[2][k] = A[2][i];
			C[1][k] = A[1][i];
			C[0][k++]=A[0][i++];
		}
		
		while(j != n){
			C[2][k] = B[2][j];
			C[1][k] = B[1][j];
			C[0][k++]=B[0][j++];
		}
		
		return C;
	}
}


public class Jobs {
	int n;
	static int start[],employer[];
	
	/**
	 * This method computes the number of intervals that need to be scheduled 
	 * 
	 * @param n			int 	number of intervals
	 * @param finish	int[]	funishing time of the intervals
	 * 
	 */
	private static void intervalSchedule(int n, int[] finish) {
		
		
		/*System.out.println("sorted output:");
		for(int i =0; i<n ; i++){
			System.out.println(start[i]+" " + finish[i]+" "+employer[i]);
		}*/
		
		
		int selected[] = new int[n];
		int selectStart[] = new int[n];
		boolean first = true;
		int count = 0,emp=0, solution = 0;
		
		for(int i = 0; i<n; i++){
			
			//if the interval is the first one, schedule it as it is sorted by finish time
			if(first){
				selected[solution]= finish[i];
				selectStart[solution++] = start[i];
				emp = employer[i];
				first = false;
				count++;
			}
			
			//otherwise check the start time of the current interval
			//with the finish time of previously selected interval
			else{
				if(start[i] >= selected[solution-1] && employer[i] != emp){
					selected[solution]= finish[i];
					selectStart[solution++] = start[i];
					emp = employer[i];
					count++;
				}
			}
		}
		
		System.out.println(count);	//display the count
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int n;
		n = scan.nextInt();
		start = new int[n];
		int finish[] = new int[n];
		employer = new int[n];
		
		for(int i=0;i<n;i++){
			start[i] = scan.nextInt();
			finish[i] = scan.nextInt();
			employer[i] = scan.nextInt();
		}
		
		int details[][] = new int[3][n];
		
		for(int i=0;i<n;i++){
			details[0][i] = finish[i];
			details[1][i] = start[i];
			details[2][i] = employer[i];
		}
		myMergeSort1 ms= new myMergeSort1();
		details = ms.mergeSort(details, n);
		
		for(int i=0;i<n;i++){
			finish[i] = details[0][i];
			start[i] = details[1][i];
			employer[i] = details[2][i];
		}
				
		intervalSchedule(n,finish);
		
		scan.close();
		
	}

}
