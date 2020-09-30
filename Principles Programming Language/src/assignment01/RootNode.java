package assignment01;

import java.util.Collection;

/**
* Root Node
* @author Kevin Chen
* @version 1.0
*/


public class RootNode extends Node{
	
	private int min, max;
	
	//Constructor for root node
    public RootNode(int min, int max, Collection<Node> nodes) {
    	super(false, nodes);
    	this.min = min;
    	this.max = max;
    }
    
    //Get the range minimum of root node 
    public int getMin() {
    	return this.min;
    }
    
    //Get the range maximum of root node
    public int getMax() {
    	return this.max;
    }

}
