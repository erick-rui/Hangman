package dictionary;

import java.io.FileNotFoundException;
import java.util.Scanner;

import trees.BinaryTree;

public class userDict extends Dictionary {
	
	private BinaryTree BT;
	private Scanner r;

	public userDict() throws FileNotFoundException {
		super();
		BT = new BinaryTree();
		BT.buildTree(dictionary);
		
	}

	
	public String getWord() {
		r = new Scanner(System.in);
		System.out.print("Enter a word:");
		String userWord = r.nextLine();
		return correction(userWord);		
	}
	
	public void test() {
		System.out.println(bruteForceAlgorithm("feather"));
		System.out.println(BST_Algorithm("feather"));
		System.out.println(DBC_Algorithm("feather"));
	}
	
	public String correction(String s) {
		r = new Scanner(System.in);
		if(BT.containsNode(s) || dictionary.contains(s))
			return s;
		else {
			System.out.println("Couldn't find that word in the Dictionary. Did you mean any of these?");
			System.out.println("[1]Brute Force found: " + bruteForceAlgorithm(s));
			System.out.println("[2]Binary Search tree found: " + BST_Algorithm(s));
			System.out.println("[3]Decrease by Constant found: " + DBC_Algorithm(s));
			System.out.println("[4]Add word to Dictionary: " + s);
			System.out.print("Choose an option:  ");
			int option = r.nextInt();
			switch(option) {
			case 1:
				return bruteForceAlgorithm(s);
			case 2:
				return BST_Algorithm(s);
			case 3:
				return DBC_Algorithm(s);
			case 4:
				BT.addNode(s);
				return s;
			}

		}
		return null;
		
	}
	
	public String bruteForceAlgorithm(String value) {
		int index = 0, streak = 0;
		for (int counter = 0; counter < dictionary.size(); counter++) { 
			  int newStreak = 0;
	          String str = dictionary.get(counter); 
	          for(int c = 0; c < value.length() &&  c < str.length(); c++) {
	        	  if(str.charAt(c) != value.charAt(c))
	        		  break;
	        	  else {
	        		  newStreak++;
	        		  if(newStreak > streak) {
	        			  index = counter;
	        			  streak = newStreak;
	        		  }
	        	  }
	        		  
	          }
		}
		return dictionary.get(index);
	}
	
	public String DBC_Algorithm(String value) {
		int c = binarySearch(value);
		return dictionary.get(c);
	}
	
	public int binarySearch(String value) {
		int low = 0;
        int high = dictionary.size() - 1;
        int mid = (low + high) / 2;

        while (low <= high && !dictionary.get(mid).equalsIgnoreCase(value)) {

            if (dictionary.get(mid).compareTo(value) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }

            mid = (low + high) / 2;

            if (low > high) {
                mid = -1;
                return low;
            }

        }
        return mid;
	}
	
	public String BST_Algorithm(String value) {
		return BT.findClosestValue(value);
	}
}
