/*
 * Merge.java
 * 
 * Version : 1.0 Date : 02-22-2015
 * 
 * Revisions : $Log Initial version$
 * 
 */

import java.util.Scanner;

/**
* The following java file sorts k sorted arrays into a single array using merge sort algorithm.
*  
* @author Dhruv Gala (dmg7937)
* 
* Sample input/output
* 
* 4
* 3
* 6 14 19
* 5
* 1 3 25 38 49
* 1
* 16
* 9
* 2 4 7 10 11 42 66 68 81
* 
* 1 2 3 4 6 7 10 11 14 16 19 25 38 42 49 66 68 81 
*/

class myArrayList{
	public int[] myArray;
    public int size = 0;
    public int arraylength;
     
    /**
     * This is the basic data structure of ArrayList (self-defined).
     * 
     * @param arrayLength
     */
    public myArrayList(int arrayLength){
    	this.arraylength = arrayLength;
        myArray = new int[arrayLength];
    }
    
    
    /**
     * 
     * @param index
     * @return
     */
    public int get(int index){
            return myArray[index];
    }
    
    
    
    /**
     * This method adds the element to the arraylist.
     * 
     * @param value
     */
    public void add(int value){
        if(myArray.length-size <= 0){
            increaseListSize();
        }
        myArray[size++] = value;
    }
    
    
    
    /**
     * This method increases the size of the arrayList dynamically by a factor of 2.
     * 
     */
	public void increaseListSize() {
		int maxSize = size*2;
		int[] newArray = new int[maxSize];
		for(int i=0;i<size;i++)
			newArray[i] = myArray[i];
		myArray = newArray;
	}
	
}


public class Merge{
	static int k;
	static myArrayList list[];
	
	
	/**
	 * This method displays the final sorted List.
	 * 
	 * @param list
	 * @param i
	 */
	static void display(myArrayList[] list,int i){
			for(int index=0;index<list[i].size;index++){
				System.out.print(list[i].get(index)+" ");
			}
			
			System.out.println();
	}
	
	
	
	/**
	 * This method feeds the input into the program.
	 * 
	 */
	static void feedInput(){
		Scanner takeInput = new Scanner(System.in);
		k = Integer.parseInt(takeInput.nextLine());
		
		list = new myArrayList[k];
		
		for(int i =0; i < k; i++){
			int length = Integer.parseInt(takeInput.nextLine());
			
			list[i] = new myArrayList(length);
			String thisList = takeInput.nextLine();
			
			for(String tokens: thisList.split(" "))
				list[i].add(Integer.parseInt(tokens));
		}
		
		takeInput.close();
	}
	
	
	
	
	/**
	 * This code plays the role of initial call and base case
	 * for the recursive calls to the same method.
	 * 
	 * @param lists
	 * @return
	 */
	public static myArrayList mergeKLists(myArrayList[] lists) {
        if (lists.length == 0){
            return null;
        }
        
        return mergeKLists(lists, 0, lists.length - 1);
    }
	
	
	
	
    /**
     * This method recursively divides the number of list until 2 list are found at 
     * the middle and hence calls the merge method.
     * 
     * @param lists
     * @param left
     * @param right
     * @return
     */
    public static myArrayList mergeKLists(myArrayList[] lists, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            return merge(mergeKLists(lists, left, mid), mergeKLists(lists, mid + 1, right));
        }
        return lists[left];
    }
    
    
    
    
    /**
     * This code performs the actual merging of two lists.
     * 
     * @param A
     * @param B
     * @return
     */
    public static myArrayList merge(myArrayList A, myArrayList B) {
    	
    	myArrayList mergedList = new myArrayList(A.size+B.size);
    	int pointerA = 0, pointerB = 0, pointerC = 0;
    	
    	while(pointerC <= mergedList.arraylength && pointerA < A.size && pointerB < B.size){
    		if(A.get(pointerA) < B.get(pointerB)){
    			mergedList.add(A.get(pointerA++));
    		}
    		else{
    			mergedList.add(B.get(pointerB++));
    		}
    		pointerC++;
    	}
    	
    	if(pointerA == A.size){
    		while(pointerB < B.size){
	    		mergedList.add(B.get(pointerB++));
	    		pointerC++;
	    	}
    	}
    	
    	if(pointerB == B.size){
    		while(pointerA < A.size){
	    		mergedList.add(A.get(pointerA++));
	    		pointerC++;
	    	}
    	}
    		
    	return mergedList;
    }
	
    
    
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		feedInput();
		list[k-1] = mergeKLists(list);
		display(list, k-1);
	}
}