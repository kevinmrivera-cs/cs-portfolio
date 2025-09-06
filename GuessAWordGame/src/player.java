import java.util.Scanner;

public class player {
    public final Scanner userInput = new Scanner(System.in);
    private final int DEFAULT_ATTEMPTS = 3;
    private int attempt;
    private String userChoice;

    public player() {
        setAttempt(); // gives a user a choice to select amount of attempts for guessing a word;
    }

    private void setAttempt() {
        System.out.print("Enter amount ( integer numbers ) of attempts you want to guess a word: ");
        if(!userInput.hasNextInt()) {
            System.out.println("Invalid input, you will get a default number which is " + DEFAULT_ATTEMPTS + " attempts");
            userInput.nextLine(); //clear tokens
            attempt = DEFAULT_ATTEMPTS;
        } else {
            attempt = userInput.nextInt();
            userInput.nextLine(); //clear token
        }
    }

    /**
     * This method lets user guess a valid word
     * @param secretWord
     */
    public void userGuess(String secretWord) {
        System.out.print("Guess a word: " );
        userChoice = userInput.nextLine();
        validWord(secretWord); // checks if user choice a valid word
    }

    private void validWord(String secretWord) {
        while(!userChoice.matches("[a-zA-Z]+") || !(userChoice.length() == secretWord.length())) { //only letters and 1 word, pass if it is not true
            System.out.println("Error! Please make sure its only letters, 1 word and same word length!");
            userChoice = userInput.nextLine();
        }
    }

    /**
     * This decrease the attempt per user guess
     */
    public void wrongGuess() {attempt--; }

    /**
     * check to see if user has no more attempts
     * @return boolean
     */
    public boolean zeroAttempt(){ return attempt == 0;}

    //Getter method
    public int getAttempt() { return attempt; }

    public String getUserChoice() { return userChoice; }

    public Scanner getScanner() { return userInput; }
}
