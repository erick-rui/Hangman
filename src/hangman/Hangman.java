package hangman;

import java.util.ArrayList;
import java.util.Scanner;

public class Hangman {

	protected String guesses;
	protected int fails;
	protected String[] lines;
	protected String word;
	protected ArrayList<String> dictionary;
	
	public Hangman(String word) {
		this.word = word;
	    guesses = "";
	    fails = 0;
	    lines = new String[]{"-------",
	            "|      ",
	            "|      ",
	            "|      ",
	            "|      ",
	            "|      "};
	}

	
	public String getGuesses() {
		return guesses;
	}


	public void setGuesses(String guesses) {
		this.guesses = guesses;
	}


	public int getFails() {
		return fails;
	}


	public void setFails(int fails) {
		this.fails = fails;
	}


	public String[] getLines() {
		return lines;
	}


	public void setLines(String[] lines) {
		this.lines = lines;
	}


	public String getWord() {
		return word;
	}


	public void setWord(String word) {
		this.word = word;
	}


	public ArrayList<String> getDictionary() {
		return dictionary;
	}


	public void setDictionary(ArrayList<String> dictionary) {
		this.dictionary = dictionary;
	}

	
	private boolean checkGuesses() {
		int f = 0;
		boolean result = true;
	    for (char c: guesses.toCharArray()) {
	    	// see if the character is not in the players guess
	    	System.out.println();
	        if(word.indexOf(c) == -1){
	        	f++;
	        }
	            	
	    }
	    for (char c: word.toCharArray()) {
	    	// see if the character is not in the players guess
	    	System.out.println();
	        if(guesses.indexOf(c) == -1){
	            result = false;
	        }
	            	
	    }
	    fails = f;
	    System.out.println("fails: " + f);
	    return result;
	}
	
	public boolean isOver() {
		Scanner r = new Scanner(System.in);
		boolean result = false;
		if(checkGuesses()){
        	System.out.println("You won!!");
        	System.out.print("Press enter to continue");
        	r.nextLine();
            return true;
        }if (fails == 7){
        	System.out.println("The word was " + word);
        	System.out.println("You Lose.");
        	System.out.print("Press enter to continue");
        	r.nextLine();
        	return true;
        }
		return result;
	}
	
	public void askForGuess() {
		Scanner r = new Scanner(System.in);  // Create a Scanner object
		while(true) {
			System.out.print("Enter a new char:");
		    String guess = r.next();  // Read user input

		    //if guess is not already in guesses
		    if(guesses.indexOf(guess) == -1) {
		    	guesses += guess;
		    	break;
		    }
		}
	    
	}
	
	private void printHangManUI() {
		switch(fails) {
		case 1:
			lines[1] = "|   O     ";
			break;
		case 2:
			lines[2] = "|   |     ";
			break;
		case 3:
			lines[2] = "|  /|     ";
			break;
		case 4:
			lines[2] = "|  /|\\    ";
			break;
		case 5:
			lines[3] = "|   |     ";
			break;
		case 6:
			lines[4] = "|  /      ";
			break;
		case 7:
			lines[4] = "|  / \\     ";
			break;
		}
	    
		for(int i = 0; i < lines.length; i++) {
			System.out.println(lines[i]);
		}
	}
	
	private void printGuesses() {
	    System.out.print("Guessed Chars: ");
	    for(char c: guesses.toCharArray())
	        System.out.print(c + " ");
	    System.out.println();
	}
	
	private void printWord() {
	    System.out.println("WORD: ");
		for (char c: word.toCharArray()) {
	    	// see if the character is in the players guess
	        if(guesses.indexOf(c) != -1)    
	            System.out.print(c);
	        else {
	        	System.out.print("_ ");
	        }
	            	
	    }
		System.out.println();
	}
	
	public void print() {
		printHangManUI();
		printGuesses();
		printWord();
	}
	    
	

}
