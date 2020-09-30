package assignment01;

import java.util.Collection;
import java.util.HashSet;

/**
* Leaf Node
* @author Kevin Chen
* @version 1.0
*/


public class LeafNode extends Node{
	
	private Collection<Integer> values = new HashSet<Integer>();
	
	//Constructor for leaf node
	public LeafNode(Collection<Integer> values) {
    	super();
    	this.values = values;
    }
	
	//Get an array/list of values in the node
	public Collection<Integer> getValues() {
		return this.values;
	}
	
	//Add value to the list/array
	public void addValues(int number) {
		this.values.add(number);
	}
}
