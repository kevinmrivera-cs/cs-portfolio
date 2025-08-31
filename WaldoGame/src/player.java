
/**
 * This object class store player status during the game
 *
 * @author Kevin Munoz-Rivera
 * @version 6/11/25
 */

//Import Scanner from java util library
import java.util.Scanner;

public class player {
    //Instance variables
    private int row;
    private int col;
    private int hintCount;
    /**
     * This method will check if the user input was valid
     * if not stay until correctly input format
     * @param String[] user guess, object gameboard
     * @return boolean if player guess was valid
     */
    public boolean checkValid(String[] playerGuess, gameboard board) {
        //Create variables
        boolean isValid = true;
        //Check if user enter only int number
        try{
            //Check if user enter 2 numbers
            if(!(playerGuess.length == 2)) {
                System.out.println("Please only 2 numbers to input");
                isValid = false;
            } 
            
            int height = Integer.parseInt(playerGuess[0]);
            int width = Integer.parseInt(playerGuess[1]);    
            
            //Check if user enter postive and fits in the gameboard
            if(height <= 0 || width <= 0 || height > board.getGameBoardHeight() || width > board.getGameBoardWidth()) {
                System.out.println("Please only postive number and make sure numbers fits in the gameboard");
                isValid = false;
            }
            
            //Check if user guess has already guess that location
            if(!(board.isEmptySlot(height - 1, width - 1))) {
                System.out.println("You have already guess this location");
                isValid = false;
            }
            
        } catch (NumberFormatException e) { //Catch when we can not turn the String to Int
            System.out.println("Please Integer number only");
            isValid = false;
        } catch (ArrayIndexOutOfBoundsException e) {//Catch when String array is not 2
            isValid = false;
        }
        
        //return the result
        return isValid;
    }
    
    /**
     * This method will collect user guessing location
     * @param object Class gameboard
     */
    public void playerGuess(gameboard board) {
        //Create Scanner
        Scanner input = new Scanner(System.in);
        
        //Ask user to guess
        System.out.print("Enter 2 numbers to guess waldo location(First number is height, second number is width): ");
        
        //Save that info and make it to String array
        String[] guess = input.nextLine().trim().split("\\s+");
        
        //Pass the method to see if it is valid, if not valid stay there until proper format
        while(!checkValid(guess, board)) {
            System.out.print("Please try again : "); 
            guess = input.nextLine().trim().split("\\s+");
        }
        
        //pass method to save player valid guess location
        setPlayerHeight(Integer.parseInt(guess[0]));
        setPlayerWidth(Integer.parseInt(guess[1]));
        
        //Close Scanner
        input.close();
    }
    
    /**
     * Setter method for player guessing waldo location
     * @param int player height
     */
    public void setPlayerHeight(int height) {
        row = height - 1; //User will type the first row as 1, subtract 1 to fit array properly
    }
    
    /**
     * Setter method for player guessing waldo location
     * @param int player height
     */
    public void setPlayerWidth(int Width) {
        col = Width - 1; //User will type the first coloum as 1, subtract 1 to fit array properly
    }
    
    /**
     * Getter method to retrieve player guessing location
     * @return int player height
     */
    public int getPlayerHeight() {
        return row;
    }
    
    /**
     * Getter method to retrieve player guessing location
     * @return int player width
     */
    public int getPlayerWidth() {
        return col;
    }
    
    /**
     * This method allows a player to get hints
     * @param object waldo
     */
    public void getHint(waldo waldo) {
        //Declare a variable
        hintCount++;
        String message = "Hint " + hintCount;
        
        //Give 3 hints to player only
        if(hintCount <= 3) {
            if(hintCount == 3) { // Special message for last hint
                message = "LAST HINT!";
            }
            
            //See if row was incorrect then give hint
            if(row < waldo.getWaldoHeight()) {
                message += " Height is higher!"; 
            } else if(row > waldo.getWaldoHeight()) {
                message += " Height is lower!";
            }
                
            //See if coloumn was incorrect then give hint
            if(col < waldo.getWaldoWidth()) {
                message += " Width is higher!"; 
            } else if(col > waldo.getWaldoWidth()) {
                message += " Width is lower!";
            } 
        } else {
            message = "NO MORE HINTS LEFT!";
        }
        
        System.out.println(message);
    }
}