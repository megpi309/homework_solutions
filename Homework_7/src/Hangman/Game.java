package Hangman;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Game extends JFrame{

	// GUI components
	protected JLabel wordLabel, attemptsLabel, timeLabel ;
	protected JTextField inputField;
	// The word the player has to guess (fixed for now)
	protected String currentWord = "propaganda";
	// Stores the letters guessed by the player
	protected ArrayList<Character> guessedLetters = new ArrayList<>();
	// Number of tries left
	protected int attemptsLeft = 6;
	// Time left (not yet functional – stays at 60)
	protected int timeLeft = 60;
	
	// Sets up the window and its components
	public Game () {
	setTitle ("Hangman Game");
	setSize (400, 250);
	setDefaultCloseOperation ( EXIT_ON_CLOSE );
	setLayout (new GridLayout (6, 1));
	// Button to start a new game
	JButton startButton = new JButton (" Start Game ");
	add( startButton );
	// Label showing the guessed word with _ for unknown letters
	wordLabel = new JLabel (" Word: ",SwingConstants.CENTER );
	add(wordLabel);
	// Label showing how many tries are left
	attemptsLabel = new JLabel (" Attempts left: " + attemptsLeft ,
	SwingConstants .CENTER );
	add( attemptsLabel );
	// Label showing the time left (not yet changing)
	timeLabel = new JLabel (" Time left: " + timeLeft ,
	SwingConstants .CENTER );
	add( timeLabel );
	// Input field where the player types their guess
	inputField = new JTextField ();
	inputField . setHorizontalAlignment ( JTextField .CENTER );
	inputField . setEnabled (false ); // initially inactive
	add( inputField );
	// Starts the game when button is clicked and disables restart
	startButton . addActionListener (e -> {
	startGame ();
	startButton . setEnabled (false );
	});
	// You will implement this method
	inputField.addActionListener (e -> processInput());
	setVisible (true );
	}
	
	
	// Resets game state when starting a new game
	protected void startGame () {
	guessedLetters .clear ();
	attemptsLeft = 6;
	timeLeft = 60;
	updateDisplay ();
	inputField.setEnabled (true );
	inputField.requestFocus ();
	}
	// Updates the word , attempts , and time on the screen
	protected void updateDisplay () {
	StringBuilder display = new StringBuilder ();
	for (char c : currentWord . toCharArray ()) {
	if ( guessedLetters .contains(c)) {
	display.append(c). append (" ");
	} else {
	display.append ("_");
	}
	}
	wordLabel.setText ("Word: " + display.toString ());
	attemptsLabel.setText (" Attempts left: " + attemptsLeft );
	timeLabel.setText ("Time left: " + timeLeft );
	}
	/* TODO Task 1: Add ActionListener for keyboard input on
	inputField and process the input */
	public void processInput () {
		String text = inputField.getText().toLowerCase().trim();;
		inputField.setText("");
		
		if(text.length() != 1 || !Character.isLetter(text.charAt(0))) {
			System.out.println("Wrong Input! Please try again.");
		}
		else {
		    handleGuess(text.charAt(0));
		}
	}
	/* TODO Task 1: Check if the guessed letter is in the
	word and handle result */
	protected void handleGuess (char guess) {
		// 1. Duplicate check
	    if (guessedLetters.contains(guess)) {
	        System.out.println("You already guessed '" + guess + "'. Try a different letter.");
	        System.out.println("Input received: [" + guess + "]");
	        return;
	    }

	    // 2. Add new guess
	    guessedLetters.add(guess);

	    // 3. Check if incorrect
	    if (!currentWord.contains(String.valueOf(guess))) {
	        attemptsLeft--;
	    }

	    // 4. Update word display
	    updateDisplay();

	    // 5. Win or lose?
	    if (hasWon()) {
	        endGame(true);
	    } else if (attemptsLeft <= 0) {
	        endGame(false);
	    }
	
	}
	/* TODO Task 1: Check if the player has guessed
	all letters correctly */
	private boolean hasWon() {
		for (int i = 0; i < currentWord.length(); i++) {
	        char c = currentWord.charAt(i);
	        if (!guessedLetters.contains(c)) {
	            return false;
	        }
	    }
	    return true;
	}
	// TODO Task 1: End the game and show a message
	protected void endGame(boolean won) {
		if(won) {
			System.out.println("You won! The correct word was: " + currentWord);
		}
		else {
			System.out.println(currentWord + " was sadly the wrong word. You lost!");
		}
		inputField.setEnabled(false);
		System.exit(0);
	}

}
