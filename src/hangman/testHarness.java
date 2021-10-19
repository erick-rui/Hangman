package hangman;

import java.util.Scanner;

import dictionary.*;


public class testHarness {

	public static void main(String[] args) {
		Scanner r = new Scanner(System.in);
		
		while(true) {
			printMenu();
			System.out.print("Input:");
			int input = r.nextInt();
			r.nextLine();
			clear();
			
			if(input == 9) {
				System.out.println("BYE");
				break;
			}
			if(input == 2) {
				System.out.println("====== About ======");
				System.out.println("Hello, my name is Erick and this is my hangman game. ");
				System.out.println("This game has two modes: Computer mode and User mode");
				System.out.println("Computer Mode generates a random word while User mode lets you input");
				System.out.println("any word with a built in autocorrect. ");
				System.out.println("Press enter to go back ");
				r.nextLine();
				clear();
			} else if(input == 1) {
				
				System.out.println("Game modes:");
				System.out.println("1. Computer Generated Words");
				System.out.println("2. User Provided Words");
				System.out.print("Please choose a number:");
				int gameMode = r.nextInt();
				
				Dictionary dict;
				if(gameMode == 1) {
					dict= new DictionaryFactory().getDictionary("COMPUTER");
				}else {
					dict= new DictionaryFactory().getDictionary("USER");
				}
				String word = dict.getWord();
				
			    Hangman game = new Hangman(word);
			    while(game.getFails() < 7) {
			        if(game.isOver()){
			            clear();
			            break;
			        }
			        game.print();
			        game.askForGuess();
			  
			    }
			}		
		}
	}
	
	private static void printMenu() {
		System.out.println("====== HANGMAN ======");
		System.out.println("Enter 1 to Start");
		System.out.println("Enter 2 for About");
		System.out.println("Enter 9 to Quit");

	}

	private static void clear() {
		for(int i = 0; i < 20; i++)
			System.out.println();
		

	}

}