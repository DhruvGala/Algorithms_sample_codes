/*
 * HuffmanDecoder.java
 * 
 * Version : 1.0 Date : 03-15-2015
 * 
 * Revisions : $Log Initial version$
 * 
 */

import java.util.Scanner;

/**
 * The following java file decodes the encoded bits according to Huffman's Decoding
 * algorithm. The data structure of a binary tree was implemented to store the symbols.
 * 
 * @author Dhruv Gala (dmg7937)
 *
 * sample Input/Output
 * 11
 * a 0101
 * as 1000
 * fleece 1101
 * had 0100
 * lamb 00 
 * little 011 
 * mary 111
 * snow 1001
 * was 1010
 * white 1011 
 * whose 1100
 * 66
 * 111010001010110001100011001110100010101100110011011010101110001001
 * 
 * maryhadalittlelamblittlelamblittlelambmaryhadalittlelambwhosefleecewaswhiteassnow
 */

class Node{
	String data;
	Node one,zero;
	
	/**
	 * Data structure implementation.
	 * 
	 * @param data
	 */
	Node(String data){
		this.data = data;
		one = zero = null;
	}
}

class HuffmanTree{
	Node root;
	
	/**
	 * 
	 */
	public HuffmanTree() {
		root = null;
	}
	
	
	/**
	 * This method is the actual logic of adding symbols to the tree data structure
	 * 
	 * @param data
	 * @param code
	 */
	public void addThisNode(String data, String code){
		Node addThis = new Node(data);
		Node traveler = root;
		int i;
		if(root == null){
			root = new Node("blank");
			traveler = root;
		}
		
		/*
		 * This for loop traverses to the level before the last expected level of 
		 * the symbol according to the input provided.
		 * 
		 */
		for(i=0;i<code.length()-1;i++){
			switch(code.charAt(i)){
			case '0':
				if(traveler.zero == null ){
					traveler.zero = new Node("blank");
				}
				traveler = traveler.zero;
				break;
			
			case '1':
				if(traveler.one == null){
					traveler.one = new Node("blank");
				}
				traveler = traveler.one;
				break;
			
			}
		}
		
		/*
		 * This switch case adds the symbol to the dictionary.
		 */
		switch(code.charAt(i)){
		case '0':
			traveler.zero = addThis;
			break;
		
		case '1':
			traveler.one = addThis;
			break;
		}
	}
	
	
	
	/**
	 * returns the node to the "ONE" link of the current node.
	 * 
	 * @param thisNode
	 * @return
	 */
	public Node goOne(Node thisNode){
		return thisNode.one;
	}
	
	
	
	/**
	 * returns the node to the "ZERO" link of the current node.
	 * 
	 * @param thisNode
	 * @return
	 */
	public Node goZero(Node thisNode){
		return thisNode.zero;
	}
}

public class HuffmanDecoder {
	
	
	
	/**
	 * This method checks if the decoding logic has reached to any leaf node, i.e.
	 * encountered any symbol for the encoded bits so far traversed.
	 * 
	 * @param atThisNode
	 * @return
	 */
	public static boolean reachedAtTheLeaf(Node atThisNode){
		return atThisNode.data != "blank";
	}
	
	
	
	/**
	 * This method prints the symbol from the tree.
	 * 
	 * @param atThisNode
	 */
	public static void decodeIt(Node atThisNode){
		System.out.print(atThisNode.data);
		
	}
	
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner takeInput = new Scanner(System.in);
		int k = Integer.parseInt(takeInput.nextLine());
		HuffmanTree myCodeTree = new HuffmanTree();
		
		/*
		 * the following for loop takes the input and stores it to myCodeTree
		 * which is a binary tree data structure.
		 * 
		 */
		for(int i=0;i<k;i++){
			String input[] = takeInput.nextLine().split(" ");
			myCodeTree.addThisNode(input[0], input[1]);
		}
		
		int n = Integer.parseInt(takeInput.nextLine());
		Node traveler = myCodeTree.root;
		String decodeThis = takeInput.nextLine();
		
		/*
		 * The following block does the actual decoding of the encoded input
		 * 
		 */
		for(int i = 0; i<n; i++){
			switch(decodeThis.charAt(i)){
				case '0':
					traveler = myCodeTree.goZero(traveler);
					if(reachedAtTheLeaf(traveler)){
						decodeIt(traveler);
						traveler = myCodeTree.root;
					}
					break;
				case '1':
					traveler = myCodeTree.goOne(traveler);
					if(reachedAtTheLeaf(traveler)){
						decodeIt(traveler);
						traveler = myCodeTree.root;
					}
					break;
			}
		}
		
		takeInput.close();
	}
	
}
