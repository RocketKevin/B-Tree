package assignment01;

import java.util.ArrayList;
import java.util.Collection;

/**
* Node Class
* @author Kevin Chen
* @version 1.0
*/

public class Node{
	
    private Collection<Node> children = new ArrayList<Node>(); // An array of child pointers 
    private boolean leaf; // Is true when node is leaf. Otherwise false 

    // Constructor for default nodes.
    public Node() {
    	this.leaf = true;
    	this.children = null;
    }

    // Constructor for secondary nodes.
    public Node(boolean leaf, Collection<Node> nodes) {
    	this.leaf = leaf;
    	this.children = nodes;
    }
    
    //Check the node type
    public boolean getLeaf() {
    	return leaf;
    }
    
    //Get the next root node
    public RootNode getRootChildren() {
    	//No node
    	if(this.children == null) {
    		return null;
    	}
		return (RootNode) this.children.toArray()[0];
    }
    
    //Get the leaf node under root node
    public LeafNode getLeafChildren() {
    	//No node
    	if(this.children == null) {
    		return null;
    	}
    	
    	if(this.children.toArray().length > 1) {
    		return (LeafNode) this.children.toArray()[1];
    	}
    	return null;
    }
    
    //Usually use when a number is inserted, but there's no leaf
    public void addLeafChildren(LeafNode node) {
    	if(this.children == null) {
    	    Collection<Node> nodeList = new ArrayList<Node>();
    		nodeList.add(null);
    		nodeList.add(node);
    		children = nodeList;
    	} else {
    		children.add(node);
    	}
    }
    
    //Amount of nodes stored
    public int getSize() {
    	return children.size();
    }
}