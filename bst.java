package program5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author barel
 * 
 * This program implement a spell checker using BST.
 */
public class SpellChecker {

    long totalWords = 0;
    long wordsFound = 0;
    long wordsNotFound = 0;
    long numOfComparisonWordsFound;
    long numOfComparisonWordsNotFound;
    int []comp = new int[1];

    //Creating 26 list, one for each alphabet letter
    BinarySearchTree[] dictionary = new BinarySearchTree[26];

    //DefaulConstructor for spellChecker object with no arguments
    SpellChecker(){
        for (int i = 0; i < dictionary.length; i++) {

            dictionary[i]= new BinarySearchTree<>();
        }//for
    }//constructor

    /**
     * building a Dictionary file
     * @throws FileNotFoundException
     * ensure that the file exist
     */
    public void buildingDictionaryFile() throws FileNotFoundException {
        File file = new File("random_dictionary.txt");
        Scanner reader = new Scanner(file);
        String line = "";

        while (reader.hasNext()) {
            line = (reader.nextLine().toLowerCase());
            dictionary[(int)line.charAt(0) - 97].insert(line);
        }//while
        reader.close(); 
    }//building dictionary method

    /* Search words in oliver.txt and compare them to the wors in random_dictionary. 
        Count the words found and not found, then calculate their average */
    public void Search(){
        try{
            FileInputStream inf = new FileInputStream(new File("oliver.txt"));
            char let;
            String str = ""; //word to be processed
            int n = 0;
            while ((n = inf.read()) != -1){
                let = (char)n;
                if (Character.isLetter(let)){
                    str += Character.toLowerCase(let);
                }//if

                if ((Character.isWhitespace(let) || let =='-') && !str.isEmpty()){

                    totalWords++;
                    if(dictionary[(int)str.charAt(0)-97].search(str, comp)){

                        wordsFound++;
                        numOfComparisonWordsFound += comp[0];   
                    }//if

                    else{
                        wordsNotFound++;
                        numOfComparisonWordsNotFound += comp[0];                     
                    }//else
                    str="";
                    comp[0]= 0;
                }//if
            }//while
            inf.close();
        }// try
        catch (IOException e){
            e.printStackTrace();
        }//catch
    }// search method

    // display output when called
    public void display() {
        double avgWordsNotFound = numOfComparisonWordsNotFound / (double)wordsNotFound;
        double avgWordsFound = numOfComparisonWordsFound / (double)wordsFound;

        System.out.println("***************************************************");
        System.out.println("                   COUNTER");
        System.out.println("***************************************************");

        System.out.println("Total number of words: " + totalWords);
        System.out.println("Total number of words found: " + wordsFound);
        System.out.println("Total number of words not found: " + wordsNotFound);
        System.out.println("Total number of comparisons for words found: "
                + numOfComparisonWordsFound);
        System.out.println("Total number of comparisons for words not found: "
                + numOfComparisonWordsNotFound);
        System.out.printf("Average comparisons for words found: %.2f\n"
                , avgWordsFound);
        System.out.printf("Average comparisons for words not found: %.2f\n"
                , avgWordsNotFound);
    }// display method

    /**
     * 
     * @param args
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {

        SpellChecker list = new SpellChecker();
        list.buildingDictionaryFile();
        list.Search();
        list.display();
    }//main method

}// class spellCheker