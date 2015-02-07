package lab3;

import java.util.Scanner;

public class LabThree {
    public static void main (String[] args) {
        
        Scanner scan = new Scanner(System.in);
        int numCases = 0;
        int numFilms = 0;
        int count = 0;
        String[] unsortedFilmArray = null;
        String[] sortedFilmArray = null;
        
        System.out.println("Please enter the number of test cases:");
        numCases = scan.nextInt();
        
        for (int i = 0; i < numCases; i++) {
            System.out.println("Please enter the number of films you have:");
            numFilms = scan.nextInt(); 
            unsortedFilmArray = new String[numFilms];
            sortedFilmArray = new String[numFilms];
            scan.nextLine();
            System.out.println("Please enter the titles of the films in current order:");
            
            for (int j = 0; j < numFilms; j++) {
                unsortedFilmArray[j] = scan.nextLine();
            }
            
            System.out.println("Please enter the titles in sorted order of favourites");
            for (int k = 0; k < numFilms; k++) {
                sortedFilmArray[k] = scan.nextLine();
            }
            
            for (int m = 0; m < numFilms; m++) {   
                if ((unsortedFilmArray[m].equals(sortedFilmArray[count]))){
                    count++;
                }
            }
            
            System.out.println();
            
            for (int q = count; q < numFilms; q++) {
                System.out.println(sortedFilmArray[q]);
            }
        }   
        scan.close();
    }
}
