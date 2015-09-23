/*
 * filename: FindKthSmallest.java
 * 
 * Version : 1.0 Date : 02-16-2015
 * 
 * Revisions : $Log Initial version$
 * 
 */

import java.util.Scanner;

/**
 * The following code finds the K th smallest integer in the given stream of integers.
 * 
 * @author DhruvGala
 *
 * sample Input/Output
 * 
 * 6
 * 159
 * 246
 * 249
 * 365
 * 1000
 * 4895
 * 45
 * 99
 * 847
 * 521
 * 427
 * 168
 * 249
 * 349
 * -1
 * 
 * 246
 */

class LinkedList{
	Node head,tail;
	
	
	/**
	 * Constructor for the data structure.
	 * 
	 */
	LinkedList(){
		head = tail = null;
	}
	
	
	/**
	 * this method adds the number to the last of the Linked List.
	 * 
	 * @param data
	 */
	void addAtLast(int data){
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
	int[] copyList(int n,LinkedList A){
		Node travel = A.head;
		int i = 0;
		int[] copiedList = new int[n]; 
		while(travel != null){
			copiedList[i++] = travel.data;
			travel = travel.next;
		}
		return copiedList;
	}
	
}

class Node{

	int data;
	Node next;
	
	/**
	 * 
	 * @param data
	 */
	Node(int data){
		this.data = data;
		next = null;
	}
}



public class FindKthSmallest {

	static int k;
	static int[] a,b;
	
	
	/**
	 * 
	 * @param input
	 * @param place
	 * @return
	 */
	static long getDigit(int input,int place){
		return (input/place % 10);
	}
	 
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @param k
	 * @return
	 */
	 public static int[] merge(int[] a , int[] b, int k){
		 int array[] = new int[k];
		 int i=0, j=0, m = 0;
		 while(m!=k){
			 if (a[i] < b[j])
		        {
		            array[m] = a[i];
		            i++;
		        }
		        else
		        {
		            array[m] = b[j];
		            j++;
		        }
		        m++;
		 }
		 
		return array;
	 }
	 
	 
	 /**
	  * 
	  * @param A
	  */
	 static void sortThis(int[] A){
		 
			
		int tempLargest = A[0];
			
			
		//this for loop finds the largest number amongst the array.
		for(int i=1; i<k; i++){
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
			for(int i=0;i<10; i++){
				B[i] = new LinkedList();
			}
				
				
			/*
			 * gets the digit at the place and adds accordingly to the array of
			 * LinkedList as required.
			 */
			for(int i=0; i < k; i++){
				int thisDigit = (int) getDigit(A[i], place);
				B[thisDigit].addAtLast(A[i]);
			}
				
				
			/*
			 * copies the elements from the Linked List array starting from 0 to 9
			 */
			int j =0;
			for(int i=0; i<10; i++){
				int[] buffer = new int[k];
				buffer = B[i].copyList(k,B[i]);
				int ki = 0;
				
				while(buffer[ki] != 0 && ki<=k && j <= k){
					A[j++] = buffer[ki++];
				}
			}
		}
	}
	
	 
	 /**
	  * 
	  * @param byThis
	  */
	 public static void replaceThis(int byThis){
		 a[k-1] = byThis;
		 sortThis(a);
	 }
	 
	 
	 
	 /**
	  * 
	  * @param args
	  */
	 public static void main(String[] args) {
		k=0;
		Scanner takeInput = new Scanner(System.in);
		k = takeInput.nextInt();
		a = new int[k];
		b = new int[k];
		int[] c = new int[k];
		
		for(int i = 0; i<k ;i++){
			a[i] = takeInput.nextInt();
		}
		sortThis(a);
		int nextInput = takeInput.nextInt(),input = 0;
		while(true){
			
			b[0]=nextInput;
			for(int i=1; i<k ; i++){
				
				input = takeInput.nextInt();
				
				if(input != -1)
					b[i] = input;
				else 
					if(input == -1)
						break;
			}
			sortThis(b);
			
			c = merge(a,b,k);
			
			a = c;
			if(nextInput == -1 || input == -1)
				break;
			nextInput = takeInput.nextInt();
			
		}

		takeInput.close();
		System.out.println(a[k-1]);
		
	 }

}
