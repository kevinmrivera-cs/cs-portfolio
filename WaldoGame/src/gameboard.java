
/**
 * This Object Class will display the gameboard
 *
 * @author Kevin Munoz-Rivera
 * @version 6/11/25
 */

//Import random from java util library
import java.util.Random;

public class gameboard {
    //Instance Variables
    private char[][] board;
    private int boardWidth;
    private int boardHeight;
    
    /**
     * This is a constructor method that sets a default gameboard size
     */
    public gameboard() {
        //Create Random
        Random rand = new Random();
        
        //Assigning instance variables
        board = new char[rand.nextInt(9 - 2) + 2][rand.nextInt(9 - 2) + 2]; //Generate a number from 2 - 8
        boardHeight = board.length;
        boardWidth = board[0].length;
        
        //Adding values in the gameboard array
        for(int row = 0; row < boardHeight; row++) {
            for(int col = 0; col < boardWidth; col++) {
                board[row][col] = ' ';        
            }
        }
        
    }
    
    /**
     * Getter method for gameboard size
     * @return int gameboard height
     */
    public int getGameBoardHeight() {
        return boardHeight;
    }
    
    /**
     * Getter method for gameboard size
     * @return int gameboard height
     */
    public int getGameBoardWidth() {
        return boardWidth;
    }
    
    /**
     * This method will check if the location is blank then user can guess that location
     * @param int player height, int player width
     * @return boolean if user can guess that location
     */
    public boolean isEmptySlot(int playerHeight, int playerWidth) {
        return board[playerHeight][playerWidth] == ' ';    
    }
    
    /**
     * This method update the gameboard after player has guess a location
     * @param object
     */
    public void updateGameBoard(waldo waldo, player player) {
        //W means waldo was found while X mark is player has already guessed
        if(waldo.isWaldoFound()) {
            board[player.getPlayerHeight()][player.getPlayerWidth()] = 'W';
        } else { 
            board[player.getPlayerHeight()][player.getPlayerWidth()] = 'X';
            player.getHint(waldo); //Gives a message to give a hint to a player
        }
        
    }
    
    /**
     * Override toString method, display the gameboard. Keep Waldo hidden if player did not guess correct
     * Also, add marks when player guess incorrectly so player won't choose the same location
     * @return String gameboard
     */
    public String toString() {
        //Create variable
        String gameboard = "";

        //Add space to for dash to fit the gameboard
        gameboard += " ";
        
        //Adding value to create a boarder line
        for(int i = 0; i < boardWidth; i++) {
            gameboard += "__";
        }
        
        //Create new line
        gameboard += "\n";
        
        //Adding value to create a gameboard layout with nested loop
        for(int row = 0; row < boardHeight; row++) {
            gameboard += "|";
            
            for(int col = 0; col < boardWidth; col++) {
                gameboard +=  board[row][col] + "|";   
            }
            
            gameboard += "\n";
            
            gameboard += " ";
        
            //Adding value to create a boarder line
            for(int i = 0; i < boardWidth; i++) {
                gameboard += "--";
            }
            
            gameboard += "\n";
        }
        
        gameboard += "\n";
        
        //return result
        return gameboard;
    }
}