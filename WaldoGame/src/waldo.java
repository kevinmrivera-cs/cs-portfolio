
/**
 * This object class will store waldo location
 *
 * @author Kevin Munoz-Rivera
 * @version 6/11/25
 */

//Import random from java util
import java.util.Random;

public class waldo {
    //Instance variable
    private int row;
    private int col;
    private boolean waldoIsFound;
    
    /**
     * Constructor method for waldo location
     * @param gameboard object
     * 
     */
    public waldo(gameboard board) {
        //Create Random
        Random rand = new Random();
        
        //Assign instance variables
        waldoIsFound = false;
        
        //These will generate a random number from 0 - gameboard Size
        row = rand.nextInt(board.getGameBoardHeight()); 
        col = rand.nextInt(board.getGameBoardWidth());
    }
    
    /**
     * Getter method to get waldo location
     * @return int waldo coloumn location
     */
    public int getWaldoHeight(){
        return row;
    }
    
    /**
     * Getter method to get waldo location
     * @return int waldo coloumn location
     */
    public int getWaldoWidth(){
        return col;
    }
    
    /**
     * Getter method to see if waldo is found
     * @return boolean waldo being found
     */
    public boolean isWaldoFound(){
        return waldoIsFound;
    }
    
    /**
     * Will check if the user found waldo
     * @param object waldo, object player
     */
    public void checkIfWaldoFound(player player) {
        waldoIsFound = row == player.getPlayerHeight() && col == player.getPlayerWidth(); 
    }
}
