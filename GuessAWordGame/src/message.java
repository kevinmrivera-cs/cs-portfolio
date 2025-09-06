/**
 * Display messages throughout the game
 */

import java.util.Scanner;

public class message {
    /**
     * This method display a intro message to user to be informed of how to play
     *
     * @param attempts
     * @param input
     */
    public static void welcomeMessage(int attempts, Scanner input) {
        System.out.println("\n\nWelcome PLAYER, to the game Word Knowledge where you have to guess a word!");

        howToPlay(attempts);


        nextPhase(input); //This method allows smooth transition for players
    }

    /**
     * This method display a message to player of how the game works
     * @param attempts
     */
    private static void howToPlay(int attempts) {
        System.out.println("\n");
        System.out.printf("%20s:\n", "How To Play");

        System.out.println("You have a total " + attempts + " attempts total to try to guess a random word. Do not worry you will be giving a hint per attempts.");
        System.out.println("A random word will ONLY CONTAIN letters, That means no number or special characters! So guess words with letters only and match the word length.");
        System.out.println("You will see symbols or even letter/s. Be sure to pay them attention!\n" );

        System.out.println("\"_\" - You will see these under dashes. This means that the letter has not been correctly guess.");
        System.out.println("\"*\"- If you see a star that means a or more characters was right but in the wrong place of the word.");
        System.out.println("\"A\" - If you see a character or more that means you guess a letter right and they are in the right spot.");
        System.out.println("HAVE FUN!");
    }

    /**
     * This method basically stops computer until player is ready to go to next phase
     */
    private static void nextPhase(Scanner input){
        System.out.print("\nPress enter to continue ");
        input.nextLine();
    }

    /**
     * Display message of user attempts left
     * @param attempt
     */
    public static void attemptsLeft(int attempt) {
        if(attempt == 1) {
            System.out.println("LAST CHANCE!");
        } else {
            System.out.println("You have " + attempt + " left.");
        }
    }

    /**
     * Give player hint to guess a word
     * @param hint
     */
    public static void displayHint(String hint) {
        System.out.println("Here is the hint " + hint + "\n that is " + hint.length() + " numbers of letters in that word");
    }

    /**
     * lets user know, wrong answer
     */
    public static void wrongAnswer() {
        System.out.println("OOPS! WRONG ANSWER, Updating hint...");
    }

    /**
     * Display a message depending if user won the game or not
     * @param gameResult
     */
    public static void endGame(boolean gameResult) {
        System.out.print("GAME OVER! ");

        if(gameResult) {
            System.out.println("CONGRANTS! You have won the game!");
        } else {
            System.out.println("SORRY! You have lost the game");
        }

    }

    /**
     * gives a user a chance if they want to play the game again
     * @param input
     * @return boolean
     */
    public static boolean retry(Scanner input) {
        System.out.print("Try again ?(Type anything but n or no to play the game again :) ) " );

        String result = input.next();

        return !result.equalsIgnoreCase("n") || !result.equalsIgnoreCase("no");
    }

    /**
     * Thank the user for playing
     */
    public static void thankYou() {
        System.out.println("THANK YOU FOR PLAYING!");
    }
}
