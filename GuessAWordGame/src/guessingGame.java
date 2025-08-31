

/**
 * This is a mini game where user has to guess a word.
 *
 * @author Kevin Munoz-Rivera
 * @version 5/14/25
 */

//Importing some util from its package
import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

//Importing some of IO from its package
import java.nio.charset.StandardCharsets;

public class guessingGame {
    public static void main(String []args) {
        /*
         * This is a mini project to get started to build my portfilo
         * list that we need to complete code
         * read file from GuessingGameWordList - done
         * have a array to save these words - done
         * randomize to choose a word - done
         * ask user to start the game and explain the rules - done
         *  user guess a word, 5 tries left, give hints per try
         *  to continue playing until game is done
         *  ask user if they want to play again then repeat
         * 
         */
        
        //Declaring a Array list to start adding words from Game word list
        ArrayList <String> wordList = getWordList();
        
        //Create random for the computer to randomly choose a word
        Random rand = new Random();
        
        //Create a variable that is a game word that was randomly selected from the word list
        //String computerWord = wordList.get(rand.nextInt(wordList.size()));
        
        //TEST
        String computerWord = wordList.get(0);
        
        //Create a variable to get total amount letters of a word
        int computerWordLength = computerWord.length();
        
        //Create a varaible of amount of attempts that the user has
        int attempts = 5;
        
        //Create 2 variables when game is done and player want to try again
        boolean gameOver = false;
        boolean tryAgain = false;
        
        //Display intro message
        welcomeMessage(attempts);
        System.out.println(computerWord); //TEST

    }
    
    /*
     * This method basically stops computer until player is ready to go to next phase
     */
    public static void nextPhase() {
        //Create a variable with scanner
        Scanner user = new Scanner(System.in);
        
        //Let user know if they are ready to continue
        System.out.println("\nPress enter to continue");
        
        String next = user.nextLine();
        
        //Close Scanner
        user.close();
    }
    
    /*
     * This method display a message to player of how the game works
     * @param int player attempts to guess a word
     */
    public static void howToPlay(int attempts) {
        System.out.println("\n\n");
        System.out.printf("%20s:\n", "How To Play");
        
        System.out.println("You have a total " + attempts + " attempts total to try to guess a random word. Do not worry you will be giving a hint per attempts.");
        System.out.println("A random word will ONLY CONTAIN letters, That means no number or special characters! So guess words with letters only.");
        System.out.println("You will see symbols or even letter/s. Be sure to pay them attention!\n" );
                           
        System.out.println("\"-\" - You will see these dashes. This means that one total amount characters of the word.");
        System.out.println("\"*\"- If you see a star that means a or more characters was right but in the wrong place of the word.");
        System.out.println("\"A\" - If you see a character or more that means you guess a letter right and they are in the right spot.");
        System.out.println("HAVE FUN!");
    }
    
    /*
     * This method display a intro message to user to be informed of how to play
     * @param int amount attempts of player can have before game over
     */
    public static void welcomeMessage(int attempts) {
        //Intro message for user
        System.out.println("Welcome PLAYER, to the game Word Knowledge where you have to guess a word!");
        
        //Call method to explain how to play
        howToPlay(attempts);
        
        //This method allows smooth transition for players
        nextPhase();
    }
    
    /*
     * This method will make sure that a word from the guessing game is valid
     * @param Scanner that is reading from the text file
     * @return String word from text file
     */
    public static String checkIfValidWord(Scanner inputStream) {
        //Create a variable to save the current line
        String line = inputStream.nextLine();
        
        //Create another scanner to read that line
        Scanner lineScan = new Scanner(line);
        
        //Create another array list to get all words in the same line 
        ArrayList<String> wordList = new ArrayList<>();
        
        //Will add every single word in the current line
        while(lineScan.hasNext()) {
            wordList.add(lineScan.next());
        }
        
        //Check if the word from text file has another word in same line or has a non character letter
        if( wordList.size() != 1 || !(wordList.get(0).matches("[a-zA-Z]+"))) {
            System.out.println("Game can not start because there is a error word from the game text file");
            System.exit(0);
        }
        
        //Close lineScan
        lineScan.close();
        
        //Once it is pass, return the single word
        return wordList.get(0);
    }
    
    /*
     * This method will check if file from GuessingGameWordList.txt is found
     * @return Scanner to read file text
     */
    public static Scanner checkFile() {
        try {
            java.io.File f = new java.io.File("GuessingWordList.txt"); // in Working directory
            if (!f.exists()) {
                throw new java.io.FileNotFoundException("Place GuessingWordList.txt in: "
                        + System.getProperty("user.dir"));
            }
            return new java.util.Scanner(
                    new java.io.BufferedInputStream(new java.io.FileInputStream(f)),
                    java.nio.charset.StandardCharsets.UTF_8
            );
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }



    /*
     * This method return all words from the guessing word file text
     * @return words list
     */
    public static ArrayList<String> getWordList() {
        //Declaring a variable
        ArrayList <String> wordList = new ArrayList<>();
        String word = "";
        //Check if file exist before adding words to arrayList
        Scanner inputStream = checkFile();
        
        //Will keep adding words as long there is words from the game words text file
        while(inputStream.hasNextLine()) {
            //Goes through once it is approve
            wordList.add(checkIfValidWord(inputStream));

        }
        
        //Close the fileInput 
        inputStream.close();
        
        //Return my wordsList
        return wordList;
    }
}
