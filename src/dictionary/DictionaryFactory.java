package dictionary;

import java.io.FileNotFoundException;

public class DictionaryFactory {

	public Dictionary getDictionary(String dictionaryType) {
		if(dictionaryType == null){
	         return null;
	      }		
	      if(dictionaryType.equalsIgnoreCase("USER")){
	         try {
				return new userDict();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	         
	      } else if(dictionaryType.equalsIgnoreCase("COMPUTER")){
	         try {
				return new computerDict();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	      } 
	      
	      return null;
	}
}
