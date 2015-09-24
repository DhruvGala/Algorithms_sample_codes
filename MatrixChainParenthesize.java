/*
 * filename: MatrixChainParenthesize.java
 * 
 * Version : 1.0 Date : 03-12-2015
 * 
 * Revisions : $Log Initial version$
 * 
 */
import java.util.Scanner;


/**
 * The following code is a classical algorithm of matrix chain multiplication problem
 * using the dynamic programming approach. 
 * 
 * @author DhruvGala
 * @author NikitaNair
 *
 * sample input/output:
 * 4
 * 2 3 1 4 2
 * 
 * ( (  A1  x  A2  )  x (  A3  x  A4  )  ) 
 */
public class MatrixChainParenthesize {

	static int input[],S[][],valueOfK[][];
	static int n;
	
	
	/**
	 * This method computes the cost function for dynamic multiplication method
	 * for determining the parenthesis position.
	 * 
	 * @param a
	 */
	static void matrixChainMultiply(int [] a){
		int len = a.length;
		n=len-1;
		S = new int[len][len];
		valueOfK = new int[len][len];
		int tmp;
		int _MAX = (int) (Math.pow(2, 32) - 1);
		
		for(int i=0;i<=n;i++){
			S[i][i]=0;
		}
		
		for(int d=1;d<n;d++){
			for(int L=1;L<=(n-d);L++){
				int R = L + d;
				S[L][R] = _MAX;
				for(int k=L;k<=R-1;k++){
					tmp = S[L][k] + S[k+1][R] + a[L-1]*a[k]*a[R];
//for debugging					System.out.print("L="+L+" R="+R+" d="+d+" k="+k+" tmp="+tmp);
//for debugging					System.out.println();
					if(S[L][R] > tmp){
						S[L][R] = tmp;
						valueOfK[L][R] = k;
//for debugging					System.out.println("S["+L+"]["+R+"]="+S[L][R]);
						
					}
				}
			}
		}
		
		
		/**
		 * the following code is for debugging and calculating the cost function 
		 * of the matrix multiplication by dynamic programming.
		 * 
		 */
		/*for(int i=1;i<len;i++){
			for(int j=1;j<len;j++){
				System.out.print(valueOfK[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println(S[1][len-1]);*/		
	}
	
	
	/**
	 * 
	 */
	static void printOptimalParens(){
		printOptimalParens(1, n);
	}
	
	
	
	/**
	 * The following code is the logic to print the parenthesis around the variables
	 * as determined by the cost function of the dynamic programming.
	 * @param i
	 * @param j
	 */
	static void printOptimalParens(int i, int j) {
		if(i==j){
			System.out.print(" A" +(i)+ " ");
		}
		else{
			System.out.print("( ");
			printOptimalParens(i,valueOfK[i][j]);
			System.out.print(" x ");
			printOptimalParens(valueOfK[i][j]+1,j);
			System.out.print(" ) ");
		}
	}
	
	
	
	/**
	 * 
	 */
	static void feedInput(){
		Scanner takeInput = new Scanner(System.in);
		int n;
		n = takeInput.nextInt();
		input = new int[n+1];
		for(int i=0; i<input.length; i++){
			input[i] = takeInput.nextInt();
		}
		takeInput.close();
	}
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		feedInput();
		matrixChainMultiply(input);
		printOptimalParens();	
	}
}
