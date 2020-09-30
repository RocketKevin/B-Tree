package assignment01;

import java.util.Collection;
import java.util.ArrayList;

/**
* B-Tree and Helper Methods
* @author Kevin Chen
* @version 1.0
*/


public class BTree {
    protected RootNode root; // Pointer to root node 
    
    //Build tree manually
    BTree() { 
    	Collection<Node> nodeCOne = new ArrayList<Node>();
    	Collection<Node> nodeCTwo = new ArrayList<Node>();
    	Collection<Node> nodeCThree = new ArrayList<Node>();
    	Collection<Node> nodeCFour = new ArrayList<Node>();
    	Collection<Node> nodeCFive = new ArrayList<Node>();
    	RootNode nodeSix = new RootNode(501, 1000, null);
    	nodeCOne.add(nodeSix);
    	RootNode nodeFive = new RootNode(401, 500, nodeCOne);
    	nodeCTwo.add(nodeFive);
    	RootNode nodeFour = new RootNode(201, 400, nodeCTwo);
    	nodeCThree.add(nodeFour);
    	RootNode nodeThree = new RootNode(101, 200, nodeCThree);
    	nodeCFour.add(nodeThree);
    	RootNode nodeTwo = new RootNode(1, 100, nodeCFour);
    	nodeCFive.add(nodeTwo);
    	RootNode nodeOne = new RootNode(1, 1000, nodeCFive);
    	this.root = nodeOne;
    } 
    
    //Check if tree is empty
    public boolean isEmpty()
    {
        return root == null;
    }   
    
    //Add Leaf Node into tree
    public Object insert(int[] num, RootNode node, int count) {
    	//When this function should stop
    	if(num.length == count) {
    		return null;
    	}
    	//Check if inputed number is too big else move on to the next root node
    	if(num[count] > node.getMax()) {
    		if(node.getRootChildren() == null) {
    			System.out.println("Inputed number is too big!");
    			System.exit(0);
    		}
    		return insert(num, node.getRootChildren(), count);
    	//If it's in between the range of the root node, add a value 
    	//unless there's no leaf then add a leaf and a value to it
    	} else if(num[count] >= node.getMin()) {
    		if(node.getLeafChildren() == null) {
    	    	Collection<Integer> value = new ArrayList<Integer>();
    	    	value.add(num[count]);
    			LeafNode leafNode = new LeafNode(value);
    			node.addLeafChildren(leafNode);
    		} else {
    			node.getLeafChildren().addValues(num[count]);
    		}
    		int next = count + 1;
    		return insert(num, this.root.getRootChildren(), next);
    	}
    	//If the value is too small tell the user and close the program
    	System.out.println("Inputed number is too small!");
    	System.exit(0);
    	return null;
    }

    //Search for the right root node
    public String searchRoot(int[] target, RootNode node, int count, ArrayList<String> result, RootNode beginRootNode) {
    	//The size of arraylist result is determine by the length of array target
    	if(result.size() != target.length) {
    		result.add("");
    	}
    	//After finish reading all the target numbers, return the result in multiple lines
    	if(target.length == count) {
    		String resultInString = arrayListToMultiLine(result, 1);
    		return resultInString;
    	}
    	//If the target number is in between the range of a root node
    	//then determine the path or result
    	if(target[count] <= node.getMax() && target[count] >= node.getMin()) {
    		result.set(count, result.get(count).concat(node.getMin() + " - " + node.getMax() + " -> "));
        	//Check if the leaf node exist
    		if(searchLeaf(node.getLeafChildren(), 0, target[count])) {
    			result.set(count, result.get(count).concat("" + target[count]));
    		} else {
    			result.set(count, target[count] + " false");
    		}
			int increaseCount = count + 1;
			return searchRoot(target, beginRootNode, increaseCount, result, beginRootNode);
    	}
    	//If the target number is greater than max range of root node,
    	//check if there's a next root node,
    	//if not it is target number not found and move on to next target
    	//else move on the the next node and store the path
    	if(target[count] > node.getMax()) {
    		if(node.getRootChildren() == null) {
    			result.set(count, target[count] + " false");
    			int increaseCount = count + 1;
    			return searchRoot(target, beginRootNode, increaseCount, result, beginRootNode);
    		}
    		result.set(count, result.get(count).concat(node.getMin() + " - " + node.getMax() + " | "));
    		return searchRoot(target, node.getRootChildren(), count, result, beginRootNode);
    	}
    	//If target number was smaller than minimum range of root node
    	//target value cannot be found and move on to the next target
    	result.set(count, target[count] + " false");
		int increaseCount = count + 1;
		return searchRoot(target, beginRootNode, increaseCount, result, beginRootNode);
    }

    //Merge the elements in the arraylist into one element
    public String arrayListToMultiLine(ArrayList<String> array, int count) {
    	//return the first element of arraylist
    	if(array.size() == count) {
    		return array.get(0);
    	}
        //format each element as a new line
    	array.set(0, array.get(0) + "\n" + array.get(count));
    	
    	int increaseCount = count + 1;
		return arrayListToMultiLine(array, increaseCount);
    }
    
    //Recursive loop through the values in the leaf node to find if target number exist
    public boolean searchLeaf(LeafNode node, int count, int target) {
    	//If no leaf node
    	if(node == null) {
    		return false;
    	}
    	//If done looping when reach to the end of array
    	if(count >= node.getValues().toArray().length) {
    		return false;
    	}
    	//Found target number
    	if((int) node.getValues().toArray()[count] == target) {
    		return true;
    	}
    	//Keep looping
    	if(count < node.getValues().toArray().length) {
    		int increaseCount = count + 1;
    		return searchLeaf(node, increaseCount, target);
    	}
		return false;
    }
}

