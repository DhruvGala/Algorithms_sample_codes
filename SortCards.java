/*
 * filename: SortCards.java
 * 
 * Version : 1.0 Date : 03-15-2015
 * 
 * Revisions : $Log Initial version$
 * 
 */
import java.util.Scanner;


/**
 * The following code is an application of Longest Increasing Sequence algorithm to
 * sort the cards in an ascending order and calculate the number of moves required to 
 * do the same.
 *  
 * @author DhruvGala
 *
 * Sample input/output:
 * 20
 * 16167 17323 25263 25061 27057 13172 7781 469 13298 27838 26525 3885 30759 26024 3466 18342 15158 5451 22894 974
 * 
 * 14
 */
public class SortCards {

	static int[] cards;
	
	/**
	 * The following code finds the longest increasing sequince using dynamic programming.
	 *  
	 * @param a
	 * @return
	 */
	static int findLongestIncreasingSubSequence(int [] a){
		int n = a.length;
		int [] S = new int[n];
		
		for(int j=0;j<n;j++){
			S[j] = 1;
			
			for(int k=0;k<j;k++){
				if(a[k]<a[j] && S[j]<S[k]+1){
					S[j] = S[k] + 1;
				}
			}
		}
		
		int max = S[0];
		for(int i=1;i<n;i++){
			if(S[i]>max){
				max=S[i];
			}
		}
		
		return (n-max);
	}
	
	
	/**
	 * 
	 */
	public static void feedInput(){
		Scanner takeInput = new Scanner(System.in);
		int n = takeInput.nextInt();

		cards = new int[n];
		for(int i=0;i<n;i++){
			cards[i] = takeInput.nextInt();
		}
		
		takeInput.close();
	}
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		feedInput();
		System.out.println(findLongestIncreasingSubSequence(cards));
	}
}
