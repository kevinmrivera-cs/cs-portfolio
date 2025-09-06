
/**
 * Collect words from word list in .txt file then generate a random word
 * once requested
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class randomWord {
    private Random rand = new Random();

    private ArrayList<String> wordList;
    private String secretWord;
    private int length;
    private StringBuilder hint;

    //Constructor
    public randomWord() {
        wordList = new ArrayList<>();
        generateWordList();
        secretWord = wordList.get(rand.nextInt(wordList.size()));
        generateDefaultHint();
        length = secretWord.length();
    }

    /**
     * Collects every word from the text file
     */
    private void generateWordList() {
        //Check if file exist before adding words to arrayList
        Scanner inputStream = checkFile();

        //Will keep adding words as long there is words from the game words text file
        while(inputStream.hasNextLine()) {

            wordList.add(checkIfValidWord(inputStream).toLowerCase()); //Checks that from the word list file contains one word per line
        }

        //Close the fileInput
        inputStream.close();
    }

    //IP TO CHANGE THIS, HELP FROM AI
    private Scanner checkFile() {
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

    /**
     *  Checking the file if every line is 1 word and contains letter
     *  if not then the game can not start
     * @param  inputStream
     * @return String
     */
    private String checkIfValidWord(Scanner inputStream) {
        //Create a variable to save the current line
        String line = inputStream.nextLine();

        //Create another scanner to read that line
        Scanner lineScan = new Scanner(line);

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
        lineScan.close();

        return wordList.get(0);
    }

    /**
     * default is ___ depending the length of the random word
     */
    private void generateDefaultHint() { hint = new StringBuilder("_".repeat(secretWord.length())); }

    /**
     * If user did not guess right, update hint if any so user can get a idea of the word is
     * @param userGuess
     */
    public void updateHint(String userGuess) {
        int[] alphaLetters = new int[26];

        for(int i = 0; i < length; i++) {
            char currentLetter = userGuess.charAt(i);

            if(alphaLetters[currentLetter - 'a']  > 0 ) { // if the letter has been check already, skip the current i
                continue;
            }

            alphaLetters[ currentLetter - 'a']++;

            for(int j = 0; j < length; j++) {
                char secretWordLetter = secretWord.charAt(j);

                if(currentLetter == secretWordLetter) {
                    if (i == j) { //Reveal the letter if the same location
                        hint.setCharAt(j, secretWordLetter);
                    } else { // else Reveal a "*", hint
                        hint.setCharAt(j, '*');
                    }
                }
            }
        }
    }

    //Getter method
    public String getSecretWord() { return secretWord; }

    public String getHint() { return hint.toString(); }
}
