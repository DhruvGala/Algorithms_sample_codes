/**
 * The following code is to compute the in-order, post-order & pre-order 
 * traversal of the Binary tree.
 * 
 * @author DhruvGala
 *
 * log: Version <1.0> constructed the underlying data structure to implement the 
 * 					  In-order,pre-order and post-order traversal. 
 */

class Node{
	Node left,right,root;
	int value;
	
	/*
	 * Default contrustor;
	 */
	public Node(){
		left = right = root = null;
		value = 0; 
	}
	
	
	/*
	 * Contructor with a parameter value to be included in the nodes.
	 */
	public Node(int value){
		this.value = value;
		left = right = root = null;
	}
	
	
	/**
	 * the following method sets the new Node to the left of the existing node.
	 * @param thisNode
	 */
	public void setToLeft(Node thisNode){
		left = thisNode;
	}
	
	
	
	/**
	 * the following method sets the new Node to the right of the existing node.
	 * @param thisNode
	 */
	public void setToRight(Node thisNode){
		right = thisNode;
	}
	
	
	/**
	 * gets the node to the left of the current node.
	 * @return
	 */
	public Node getLeft(){
		return left;
	}
	
	
	/**
	 * gets the node to the right of the current node.
	 * @return
	 */
	public Node getRight(){
		return right;
	}
	
	
	/**
	 * sets the value at the nodes.
	 * @param value
	 */
	public void setValue(int value){
		this.value = value;
	}
	
	/**
	 * gets the value stored at the nodes.
	 * @return
	 */
	public int getData(){
		return value;
	}
}

public class MyBinaryTrees {

	
}
