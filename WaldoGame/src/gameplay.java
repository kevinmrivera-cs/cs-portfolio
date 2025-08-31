
/**
 * Main Driver to play the waldo game
 *
 * @author Kevin Munoz-Rivera
 * @version 6/11/25
 */

//Import Scanner from util library
import java.util.Scanner;

//Import serval classes from io library
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.File;
public class gameplay {
    public static void main(String []args) {
        //This variable collects the total attempts of games and amount of guess it took user to find waldo
        int attempts = 0;
        int guessesTotal = 0;
        int guessAttempt = 0;
        
        //Continue the loop until player is done playing, gameplay will run once
        do {
            //Assign object classes
            gameboard gameboard = new gameboard();
            waldo waldo = new waldo(gameboard);
            player player = new player();
            
            //Increment per game attempts
            attempts++;
            
            //Reset the guessAttempt
            guessAttempt = 0;
            
            //Display the round
            System.out.println("Round " + attempts);
            
            //Main game is play until the game is over
            while(!waldo.isWaldoFound()) {
                //Display the gameboard
                System.out.println(gameboard);
                
                //Player guess where is waldo
                player.playerGuess(gameboard);
                
                //Check if player guessed waldo right
                waldo.checkIfWaldoFound(player);
                
                //Update the board
                gameboard.updateGameBoard(waldo, player);
                
                //Increment per guess
                guessAttempt++;
            }
            
            //Display the gameboard
            System.out.println(gameboard);
            
            //Add all the guessAttempt and save them
            guessesTotal += guessAttempt;
            
            //tell user the result of the game
            System.out.println("Waldo has been found!");
            System.out.println("Guesses Attempted: " + guessAttempt);
            
            //Print out new line
            System.out.println();
        }while(playAgain()); //Method that will see the user respond
        
        //End game message 
        outroMessge(attempts, guessesTotal);
    }
    
    /**
     * Provides a outro message when user is done playing the waldo game
     * @param int amount of game user played, int guesses total is total amount of guess user made to find waldo
     */
    public static void outroMessge(int attempts, int guessesTotal) {
        //Display end game message
        System.out.println("Total rounds attempted: " + attempts);
        System.out.println("Total amount of attempts it took to guess: " +  guessesTotal);
        System.out.println("Thank you for playing my version of where's waldo!");    
    }
    
    /**
     * This method ask user if they want to play the game again
     * @return boolean user reponse
     */
    public static boolean playAgain() {
        //Create Scanner variable
        Scanner input = new Scanner(System.in);
        
        //Ask user
        System.out.print("Want to go another round?" );
        
        //Save the respond
        String respond = input.next();
        
        //Close Scanner
        input.close();
        
        //Display new line
        System.out.println();
        
        //return the result
        return respond.equalsIgnoreCase("yes") || respond.equalsIgnoreCase("y");
    }
    
    /**
     * Letting user to type anything to go to the next phase
     * purpose is to let them read the message before continuing anything
     */
    public static void transition() {
        //Create scanner
        Scanner user = new Scanner(System.in);
        
        //Ask user to enter anything
        
    }
    
    /**
     * Reads a file from waldo how to play file then display a message
     */
    public static void introMessage() {
        //Declare variables to get ready to read file
        String fileName = ""; // Name of the file
        Scanner inputStream = null;
        String line = "";
        
        //Will catch and send a message if file can not be found
        try{
            File file = new File(fileName);
            inputStream = new Scanner(new FileInputStream(file));
            
            //Continue reading until no more text in the file
            while(inputStream.hasNextLine()) {
                //Save the text file line
                line = inputStream.nextLine();
                
                //Print text file line
                System.out.println(line);    
            }
            
            //Close Scanner to end reading the text file
            inputStream.close();
            
            //Method for transition
            //transition();
        } catch(FileNotFoundException e) {
            System.out.println("File can not be found, make sure it matches!");
            System.exit(0);
        }
    }
}