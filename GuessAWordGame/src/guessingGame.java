

/**
 * This is a mini game where user has to guess a word.
 *
 * @author Kevin Munoz-Rivera
 * @version 5/14/25
 */

public class guessingGame {
    public static void main(String []args) {
        boolean retry ;

        //Runs a game first time and let user if they want to try again
        do{
            boolean gameOver = false;
            boolean didWin = false;
            randomWord rand = new randomWord();
            player player = new player();
            message.welcomeMessage(player.getAttempt(), player.getScanner());

            //Main gameplay
            do{
                message.attemptsLeft(player.getAttempt());
                message.displayHint(rand.getHint());
                player.userGuess(rand.getSecretWord());

                if(player.getUserChoice().equals(rand.getSecretWord())) {
                    gameOver = true;
                    didWin = true;
                } else {
                    message.wrongAnswer();
                    player.wrongGuess();
                    rand.updateHint(player.getUserChoice());
                }
                if(player.zeroAttempt()) { gameOver = true;} //ends the game and user lose if they have no more attempts

            }while(!gameOver);

            message.endGame(didWin);
            retry = message.retry(player.getScanner());
            
        }while(!retry);
        message.thankYou();
    }

}
