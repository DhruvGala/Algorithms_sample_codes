/*
 * IndexSearch.java
 * 
 * Version : 1.0 Date : 02-08-2015
 * 
 * Revisions : $Log Initial version$
 * 
 */


import java.util.Scanner;


/**
* The following java file checks if there exist a number k, in a given Array of integers from 1 to n
* such that A[k] = k. If Yes then programs prints TRUE else it prints FALSE.
*  
* @author Dhruv Gala (dmg7937)
* 
* sample Input/Output
* 8
* -7 -3 0 1 4 6 9 14
* 
* TRUE
* 
* The first line contains the value n, indicating the size of the array
* The second line contains the integers separated by spaces
*/

public class IndexSearch {

	static int A[],n;
	
	
	/**
	 * this method searches at the middle of the array for the required index,
	 * if found will return TRUE else returns FALSE. This is implemented by recursive 
	 * binary search. Hence the time complexity of O(log n)
	 * 
	 * @param start
	 * @param end
	 * @return String
	 */
	static String searchThis(int start, int end){
		int mid = (start+end)/2;
		String found = "FALSE";
		if(start <= end){
			if(mid == A[mid])
				return "TRUE";
			else if(mid < A[mid])
				found = searchThis(start, mid);
			else if(mid > A[mid])
				found = searchThis(mid+1, end);
		}
		
		return found;
	}
	
	
	
	/**
	 * This method takes in the input from the user, and stores the same into an array.
	 */
	static void feedInput(){
		int i=1;
		Scanner takeInput = new Scanner(System.in);
		String length = takeInput.nextLine();
		n = Integer.parseInt(length);
		A = new int[n+1];
		String thisArray = takeInput.nextLine();
		for(String tokens: thisArray.split(" "))
			A[i++]=Integer.parseInt(tokens);
		takeInput.close();
	}
	
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		feedInput();
		System.out.println(searchThis(1, n));
		
	}
}
