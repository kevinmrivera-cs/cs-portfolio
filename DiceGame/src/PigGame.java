
/**
 * Rolling diced game from the book Abosulte java by Walter Savitch chapter 3 
 * programming projects number 10
 *
 * @author Kevin Munoz-Rivera
 * @version 5/13/25
 */

//Importing Random from Java util library
import java.util.Random;

//Importing Scanner from java util library
import java.util.Scanner;

public class PigGame {
    //Access both Scanner and random anywhere within class
    public static Scanner userInput = new Scanner(System.in);
    public static Random diceRoll = new Random();
    
    //Constant variable
    public static final int GAMEWINNERPOINTS = 100;
    public static final int COMPSTOPROLLING = 20; //Computer will continue to roll until hit 20 or more
    
    public static void main(String []args) {
        //Declare Variable
        boolean wantToHold;
        int lostPoints;
        
        //Declare variable with value
        boolean gameOver = false;
        boolean playerTurn = true;
        boolean playerWin = false;
        boolean compTurn = true;
        String player = "You";
        String comp = "I";
        int diceNum = 0;
        int playerTotalPoints = 0;
        int compTotalPoints = 0;
        int potentialPoints = 0;
        int previousDiceNum = 0;
        int badNumber = 1; //Skips turn if rolled a one
        
        //Intro of the game
        welcomeMessage();
        
        //The game starts and will not end until there is a winner
        while(!gameOver) {
            //Player turn to play until roll a one or game over
            while(playerTurn && !gameOver) {
                //Show previous roll 
                previousRoll(diceNum);
                
                //player goes first and continues playing until roll a one or hold
                diceNum = getDiceRoll(player);
                
                //Check if rolled a 1
                if(diceNum == badNumber) {
                    //Player skip its turn now
                    playerTurn = false;
                    
                    //coomputer turn
                    compTurn = true;
                    
                    //Save the lose points
                    lostPoints = potentialPoints;
                    
                    //reset potential points
                    potentialPoints = 0;
                    
                    //Update ScoreBoard
                    scoreBoard(potentialPoints + playerTotalPoints, player, compTotalPoints, comp);

                    //Display message that their turn is over
                    skipTurnMessage(player,lostPoints);
                    
                    } else {
                    //Save the points that the player roll and add more if they continue to roll
                    potentialPoints = potentialPoints + diceNum;
                    
                    //Update ScoreBoard
                    scoreBoard(potentialPoints + playerTotalPoints, player, compTotalPoints, comp);
                    
                    //Game over if player reach game winning points
                    if(potentialPoints + playerTotalPoints >= GAMEWINNERPOINTS) {
                        playerTurn = false;
                        gameOver = true;
                        playerWin = true;
                    } else {
                            //Option to keep rolling or hold
                            wantToHold = rollOrHold();
                    
                            if(wantToHold) {
                            //Add up all the points the player gain
                            playerTotalPoints = playerTotalPoints + potentialPoints;
                            
                            //Skip player turn
                            playerTurn = false;
                            
                            //computer turn
                            compTurn = true;
                            
                            //Reset potential points
                            potentialPoints = 0;
                            }
                    }
                }
            }
            
            //This is computer turn until either win the game or roll a 1
            while(compTurn && !gameOver) {
                diceNum = getDiceRoll(comp);
                
                //Check if rolled a 1
                if(diceNum == badNumber) {
                    //Computer skip its turn now
                    compTurn = false;
                    
                    //player turn now
                    playerTurn = true;
                    
                    //Save the lose points
                    lostPoints = potentialPoints;
                    
                    //reset potential points
                    potentialPoints = 0;
                    
                    //Display message that their turn is over
                    skipTurnMessage(comp,lostPoints);
                    
                } else {
                    //Save the points that the player roll and add more if they continue to roll
                    potentialPoints = potentialPoints + diceNum;
                    
                    
                    //Game over if player reach game winning points
                    if(potentialPoints + compTotalPoints >= GAMEWINNERPOINTS) {
                        compTurn = false;
                        gameOver = true;
                        playerWin = false;
                    } else {
                            //Will continue to roll until hit 20 or more points
                            wantToHold = compWantToHold(comp, potentialPoints); 
                    
                            if(wantToHold) {
                            //Add up all the points the player gain
                            compTotalPoints = compTotalPoints + potentialPoints;
                            
                            //computer skip turn
                            compTurn = false;
                            
                            //player turn
                            playerTurn = true;
                            
                            //Reset potential points
                            potentialPoints = 0;
                            
                            //Promt user to go to next turn
                            nextTurn();
                            }
                            
                    }
                    
                }
                
            }
            
        }
        
        //Display message of who won
        if(playerWin) {
            winnerMessage(player);
        } else {
            //Comp win
            winnerMessage(comp);
        }
    }
    
    /*
     * This method display winner of pig rolling dice game
     * @param player or computer
     */
    public static void winnerMessage(String currentPlayer) {
        //Display the winner Message
        System.out.println(currentPlayer + " won the Pig Rolling dice game! Thank you for playing");  
    }
    
    /*
     * This method check if computer hit 20 or more points then hold
     * @param String computer turn and int potential points it earned
     * @return boolean if computer want to hold or not
     */
    public static boolean compWantToHold(String comp, int potentialPoints) {
        //Declare a variable
        String message;
        // Declare a variable with value
        boolean compWantToHold = potentialPoints >= COMPSTOPROLLING;
        
        if(compWantToHold) {
            message = comp + " want to hold.";    
        } else {
            message = comp + " will continue to roll.";   
        }
        
        //Display message
        System.out.println(message);
        
        //return result
        return compWantToHold;
    }
    
    /*
     * Gives a option to hold to gain all points or roll to potential get more points
     * @return if they want to hold or roll
     */
    public static boolean rollOrHold () {
        String choice;
        boolean hold = true;
        
        //Promt user to give option to roll or hold
        System.out.println("Do you want to rolled again or hold? (type \"r\" ro roll again or \"h\" to hold)");
        
        //Save that info
        choice = userInput.next();
        System.out.println();
        
        if(choice.equals("r")) {
            hold = false;    
        }
        
        //Return of what the user type
        return hold;
    }
    
    /*
     * This method display skip turn message when rolled a one 
     * @param player or comp turn and points they lost of the current turn
     */
    public static void skipTurnMessage(String currentPlayer, int potentialPoints) {
        //Display Message
        System.out.println("That means " + currentPlayer + " lost your turn and lost " + potentialPoints + " points earned of this turn");
        
        //Goes next turn
        nextTurn();
    }
    
    /*
     * This method rolls a dice and display a message
     * @param computer or player turn
     * 
     */
    public static int getDiceRoll(String currentPlayer) {
        //Declare a variable
        int max = 7; //exclusive for rolling dice
        int min = 1; // inclusive for rolling dice
        
        int diceNum = diceRoll.nextInt(max - min) + min; // options are 1 - 6
        
        //Display a message
        System.out.println(currentPlayer + " roll a " + diceNum);
        System.out.println();
        
        //return the dice number
        return diceNum;
    }
    
    /*
     * This method display score
     */
    public static void scoreBoard(int playerScore, String player, int compScore, String comp) {
        //Let user know the ScoreBoard
        System.out.println("SCOREBOARD");
        
        //Player Score
        System.out.println(player + ": " + playerScore);   
        
        //Comp Sc
        System.out.println(comp + ": " + compScore);
        System.out.println();
    }
    
    /*
     * This method shows the previous roll
     */
    public static void previousRoll (int diceNum) {
        //Display previous roll
        System.out.println("PREVIOUS ROLL: " + diceNum);
        System.out.println();
    }
    
    /*
     * Let user know to go to next turn
     */
    public static void nextTurn() {
        //Let user know to type and enter to go next turn
        System.out.println("Type anything  and enter to continue");
        
        //Next turn
        String next = userInput.next();
        System.out.println();
    }
    /*
     * This display intro message of the game before player start their game
     */ 
    public static void welcomeMessage() {
        //Message
        System.out.println("Welcome to the Pig rolling dice game");
        
        //Goes to next turn
        nextTurn();
    }
}

