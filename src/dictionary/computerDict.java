package dictionary;

import java.io.FileNotFoundException;
import java.util.Random;


public class computerDict extends Dictionary{
	
	public computerDict() throws FileNotFoundException {
		super();
	}
	
	public String getWord(){
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(dictionary.size());
		String word = dictionary.get(index);
		return word;
	}
	
}