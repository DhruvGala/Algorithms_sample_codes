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
	 * Default constructor;
	 */
	public Node(){
		left = right = root = null;
		value = 0; 
	}
	
	
	/*
	 * Constructor with a parameter value to be included in the nodes.
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

	private Node root;
	
	
	/*
	 * default constructor
	 */
	public MyBinaryTrees(){
		root = null;
	}
	
	
	/**
	 * checks if the tree is empty.
	 * 
	 * @return
	 */
	public boolean isEmpty(){
		return root == null;
	}
	
	
	/**
	 * Inserts the value at the specified node
	 * @param value
	 */
	public void insert(int value){
		root = insert(root,value);
	}
	
	
	/**
	 * recursively adds the value at the node.
	 * 
	 * @param thisNode
	 * @param value
	 * @return
	 */
	private Node insert(Node thisNode,int value){
		if(thisNode == null){
			thisNode = new Node(value);
		}
		else{
			if(thisNode.getRight() == null){
				thisNode.right = insert(thisNode.right, value);
			}
			else{
				thisNode.left = insert(thisNode.left, value);
			}
		}
		return thisNode;
	}
	
	
	/**
	 * this method counts the nodes
	 * @return
	 */
	public int countNodes(){
		return countNodes(root);
	}
	
	
	/**
	 * this method recursively counts the node in the tree specified.
	 * @param thisTree
	 * @return
	 */
	private int countNodes(Node thisTree){
		if(thisTree == null){
			return 0;
		}
		else{
			int count = 1;
			count += countNodes(thisTree.getLeft());
			count += countNodes(thisTree.getRight());
			return count;
		}
	}
	
	
	/**
	 * The specified value is searched in the tree
	 * @param value
	 * @return
	 */
	public boolean search(int value){
		return search(root,value);
	}
	
	
	/**
	 * this method recursively searches for the value in the given tree.
	 * @param thisTree
	 * @param value
	 * @return
	 */
	private boolean search(Node thisTree,int value){
		if(thisTree.getData()==value){
			return true;
		}
		if(thisTree.getLeft() != null){
			if(search(thisTree.getLeft(),value)){
				return true;
			}
		}
		if(thisTree.getRight() != null){
			if(search(thisTree.getRight(), value)){
				return true;
			}
		}
		return false;
	}
	
}
