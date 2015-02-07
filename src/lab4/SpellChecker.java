package lab4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Spellchecker.java
 * Reads two files, one "reference" file of words, one with test words.
 * Compares both file to check spelling errors. Once with brute force
 * linear search, other with recursive binary search. 
 * @author Jessica
 * @version 1.0 - works only if reference file is 1 word per line and
 * words in alphabetical order.
 */
public class SpellChecker {
    
    /**
     * Linear search
     * @param a array of reference words
     * @param key string to search for
     * @return true on error (or no matches)
     */
    public static boolean SeqtlSearch(String[] a, String key) {
      //errorcheck - REMOVE
        System.out.println("start seq search");
        int position = 0;
        int length = a.length;

        while (position < length - 1) {
            if (a[position].equals(key)){
                return false;
            }
            position++;
        }  
      return true;

    }
    
    /**
     * Binary search
     * @param a reference array
     * @param key: string key value
     * @param head: head pointer
     * @param tail: tail pointer
     * @return true on no matches found (error detected)
     */
    public static boolean BinrySearch(String[]a, String key, int head, int tail) {
        
        if (head > tail) {
            return true;
        }
        
    	int midpoint = (tail + head) / 2 ;
    	
    	//errorcheck - REMOVE
    	System.out.println("MIDPOINT " + midpoint + " key " + key);
    	if (a[midpoint].equals(key)) {
    	  //errorcheck - REMOVE
    	    System.out.println("SOMEHOW CORRECT, RECURSION WIN: " + 
    	            a[midpoint] + " at " + midpoint);
    	    return false;
    	} else if (a[midpoint].compareTo(key) > 0) {
    	    return BinrySearch(a, key, head, midpoint - 1);
    	} else {
    	   return BinrySearch(a, key, midpoint + 1, tail);
    	}
    }
    
    /**
     * Opens and reads a file, then copies the contents to an array.
     * @param path: full path of the file.
     * @param size: the number of lines in the file.
     * @return: the array of words parsed from the file.
     * @throws IOException
     */
    public static String[] readFile(String path, int size) throws IOException {
        
      //errorcheck - REMOVE
        System.out.println("start dumbest method ever");
        
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(new File(path));
        String[] words = new String[size];
        
        while (scan.hasNextLine()) {
            for (int i = 0; i < size; i++) {
                words[i] = scan.nextLine();
            }
        }
        
      //errorcheck - REMOVE
        System.out.println("end readline");
        System.out.println("words array length " + words.length);
        
        return words;
    }
    
    /**
     * Returns the number of lines in a file. 
     * @param BufferedReader reader for FileReader opened in main.
     * @return "wordCount" currently the number of words. Actually is # of lines.
     * @throws IOException
     */
    public static int getFileLength(BufferedReader br) throws IOException {

        int wordCount = 0;
        String word = br.readLine();
        
        //precondition: one word per line
        while (word != null) {
            wordCount++;
            word = br.readLine();
        }
        
        return wordCount;
    }
    
    public static void main (String[] args) throws IOException {
        
        //hard-coded filename for dictionary source
        String inputPath = "C:\\Users\\Jessica\\workspace\\comp3711\\src\\lab4\\lab4_wordlist.txt";
        String testPath = "C:\\Users\\Jessica\\workspace\\comp3711\\src\\lab4\\testwords.txt";
        /** FileReaders for dictionary and test files. */
        FileReader fr = new FileReader (inputPath);
        FileReader fr2 = new FileReader (testPath);
        /** BufferedReaders for files opened. */
        BufferedReader br = new BufferedReader (fr); 
        BufferedReader br2 = new BufferedReader (fr2);
        /** Variable to get the number of words in dictionary file.*/
        int numWords = 0;
        /** Hold number of words in test file.*/
        int numWordsTestFile = 0;
        /** Hold count of errors found by sequential search.*/
        int countSeq = 0;
        /** Hold number of errors found by binary search.*/
        int countBinary = 0;
        /** Array of reference words.*/
        String[] dictionary; 
        /** Array of test words.*/
        String[] keyArray;
        /** Stopwatch object to time searches.*/
        Stopwatch stopwatch = new Stopwatch();
        
      //errorcheck - REMOVE
        System.out.println("post vars init.");
        
        numWords = getFileLength(br);
        numWordsTestFile = getFileLength(br2);
        
        //errorcheck - REMOVE
        System.out.println(numWords);
        System.out.println(numWordsTestFile);
        
        dictionary = new String[numWords];
        keyArray = new String[numWordsTestFile];
        
        //errorcheck - REMOVE
        System.out.println("numwords: " + numWords);
        System.out.println("numwords: " + numWordsTestFile);
        
        dictionary = readFile(inputPath, numWords);
        keyArray = readFile(testPath, numWordsTestFile);
        
        //errorcheck - REMOVE
        System.out.println("dictionary[1]" + dictionary[1]);
        System.out.println("key array length: " + keyArray.length);
        
        for (int i = 0; i < numWordsTestFile; i++) {
            //start timer
            stopwatch.start();
            if (SeqtlSearch(dictionary, keyArray[i])) {
              //errorcheck - REMOVE
                System.out.println("error at: " + i);
                countSeq++;
            }       
        }
        //stop timer
        stopwatch.stop();
        
        System.out.println("Sequential search: " + countSeq + 
                " errors in " + stopwatch.getElapsedTime());
        
        //reset timer
        stopwatch.reset();
        
        for (int j = 0; j < numWordsTestFile; j++) {
            //start timer
            stopwatch.start();
            if (!BinrySearch(dictionary, keyArray[j], 0, (dictionary.length - 1))) {
              //errorcheck - REMOVE
                System.out.println("error at: " + j);
                countBinary++;
            }
        }
        //stop timer
        stopwatch.stop();
        
        System.out.println("Binary search: " + countBinary +
                " errors in " + stopwatch.getElapsedTime());
        
        br.close();
        fr.close();
        br2.close();
        fr2.close();
    }

}
