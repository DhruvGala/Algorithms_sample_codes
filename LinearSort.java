/*
 * LinearSort.java
 * 
 * Version : 1.0 Date : 02-10-2015
 * 
 * Revisions : $Log Initial version$
 * 
 * 
 */


import java.util.Scanner;


/**
* The following java file sorts the given array of integers A[1..n].
*  
* @author Dhruv Gala (dmg7937)
* 
* sample Input/Output
* 
* 8
* 6 63 12 1 20 57 9 49
* 
* 1 6 9 12 20 49 57 63 
* 
* the first line  contains the number of integers to be sorted, the next line is the
* input for all integers which are to be sorted.
*/

class LinkedList{
	Node head,tail;
	
	LinkedList(){
		head = tail = null;
	}
	
	
	/**
	 * this method adds the number to the last of the Linked List.
	 * 
	 * @param data
	 */
	void addAtLast(long data){
		Node addThis = new Node(data);
		if(head == null){
			head = addThis;
			tail = head;
		}
		else{
			tail.next = addThis;
			tail = addThis;
		}
	}
	
	
	/**
	 * This method copies the given LinkedList to an Array and returns the array.
	 * 
	 * @param n
	 * @param A
	 * @return long[]
	 */
	long[] copyList(int n,LinkedList A){
		Node travel = A.head;
		int i = 0;
		long[] copiedList = new long[n]; 
		while(travel != null){
			copiedList[i++] = travel.data;
			travel = travel.next;
		}
		return copiedList;
	}
	
}

class Node{

	Long data;
	Node next;
	
	Node(long data){
		this.data = data;
		next = null;
	}
}

public class LinearSort{
	
	static int n;
	static long[] A;
	
	
	
	/**
	 * This method takes in the input nd stores it into an array.
	 * 
	 */
	static void feedInput(){
		int i=1;
		Scanner takeInput = new Scanner(System.in);
		String length = takeInput.nextLine();
		n = Integer.parseInt(length);
		A = new long[n+1];
		
		String thisArray = takeInput.nextLine();
		for(String tokens: thisArray.split(" "))
			A[i++]=Long.parseLong(tokens);
		
		takeInput.close();
	}
	
	
	/**
	 * I've implemented Radix sort so the time complexity O(n).
	 * 
	 */
	static void sortThis(){
		
		long tempLargest = A[1];
		
		
		//this for loop finds the largest number amongst the array.
		for(int i=2; i<=n; i++){
			if(A[i] > tempLargest)
				tempLargest = A[i];
		}
		
		/* 
		 * loops around for the number of digits the largest number in the given
		 * array contains.
		 */
		for(int place=1; place <= tempLargest; place *= 10){
			
			LinkedList B[] = new LinkedList[10];
			
			//initializes the array of LinkedList
			for(int i= 0;i<10; i++)
				B[i] = new LinkedList();
			
			
			/*
			 * gets the digit at the place and adds accordingly to the array of
			 * LinkedList as required.
			 */
			for(int i=1; i <= n; i++){
				int thisDigit = (int) getDigit(A[i], place);
				B[thisDigit].addAtLast(A[i]);
			}
			
			
			/*
			 * copies the elements from the Linked List array starting from 0 to 9
			 */
			int j =1;
			for(int i=0; i<10; i++){
				long[] buffer = new long[n];
				buffer = B[i].copyList(n,B[i]);
				int k = 0;
				while(buffer[k] != 0 && k<=n && j <= n){
					A[j++] = buffer[k++];
				}
			}
        }
		
	}
	
	
	/**
	 * This method computes the digit contained in the input number according to
	 * the place passed.
	 * 
	 * @param input
	 * @param place
	 * @return long
	 */
	static long getDigit(long input,int place){
		return (input/place % 10);
	}
	
	
	
	/**
	 * This method prints the output array.
	 */
	static void printArray(){
		for(int i = 1; i<= n; i++)
			System.out.print(A[i]+" ");
	}
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		feedInput();
		sortThis();
		printArray();
	}
}