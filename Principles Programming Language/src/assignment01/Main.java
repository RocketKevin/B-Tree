package assignment01;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

/**
* Creating B-tree, Insert numbers, and Search numbers!
* @author Kevin Chen
* @version 1.0
*/


public class Main {
	
	public static void main(String[] args){
		
		Scanner keyboard = new Scanner(System.in);
		//Ask user for a line of numbers
		System.out.println("Enter the numbers to insert in a line (1 2 3 4 5):");
		String userInput = keyboard.nextLine(); 
		//Split the numbers by the space in between each number and put it into an array
		String [] stringTokens = userInput.split(" "); 
		int [] userInputToArrInt = Stream.of(stringTokens).mapToInt(strToken -> Integer.parseInt(strToken)).toArray(); 
		
		//Create and add numbers to the tree
		BTree tree = new BTree();
		tree.insert(userInputToArrInt, tree.root.getRootChildren(), 0);
		
		//Ask user for a line of numbers to search from the tree
		System.out.println("Enter the numbers to search in a line (1 2 3 4 5):");
		userInput = keyboard.nextLine(); 
		//Split the numbers by the space in between each number and put it into an array
		stringTokens = userInput.split(" "); 
		userInputToArrInt = Stream.of(stringTokens).mapToInt(strToken -> Integer.parseInt(strToken)).toArray();
		
		//Print out the search result
		ArrayList<String> result = new ArrayList<String>();
		System.out.println(tree.searchRoot(userInputToArrInt, tree.root.getRootChildren(), 0, result, tree.root.getRootChildren()));
		
		//Confirm that the program is ending
		System.out.println("Done! Program closing.");
		keyboard.close();
	}
}
