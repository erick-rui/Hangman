package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Dictionary {
	
	protected ArrayList<String> dictionary;
	
	public Dictionary() throws FileNotFoundException {
		dictionary = buildDictionary();
	}

	public String getWord(){
		return null;
	}
	
	private ArrayList<String> buildDictionary() throws FileNotFoundException {
        File file = new File("words.txt");
        Scanner reader = new Scanner(file);
        String line = "";
        ArrayList<String> words = new ArrayList<String>();
        while (reader.hasNext()) {
            line = (reader.nextLine().toLowerCase());
            words.add(line);
        }
        reader.close(); 
        return words;
    }
	
	public void test() {
		
	}
}
