package Hangman_Interface;

import javax.swing.SwingUtilities;

public class HangmanGame extends GameGUI implements GameLogic, TimerLogic{

	private Thread timerThread;
    private boolean timerRunning;

    public HangmanGame() {
        super();
    }
    
	@Override
	public void startTimer() {
		System.out.println("Timer started");  // <-- Testausgabe
	    timerRunning = true;
	    timerThread = new Thread(() -> {
	        while (timerRunning && timeLeft > 0) {
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                return;
	            }
	            timeLeft--;
	            System.out.println("Timer: " + timeLeft);  // <-- Testausgabe

	            SwingUtilities.invokeLater(() -> timeLabel.setText("Time left: " + timeLeft));

	            if (timeLeft <= 0) {
	                timerRunning = false;
	                SwingUtilities.invokeLater(() -> endGame(false));
	            }
	        }
	    });
	    timerThread.start();
	}

	@Override
	public void handleGuess(char guess) {
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

	@Override
	public boolean hasWon() {
		for (int i = 0; i < currentWord.length(); i++) {
	        char c = currentWord.charAt(i);
	        if (!guessedLetters.contains(c)) {
	            return false;
	        }
	    }
	    return true;
		
	}

	@Override
	public void endGame(boolean won) {
		timerRunning = false; // Timer stoppen
        inputField.setEnabled(false);
        if (won) {
            System.out.println("You won! The correct word was: " + currentWord);
        } else {
            System.out.println("You lost! The correct word was: " + currentWord);
        }
        System.exit(0);
	}
	
	protected void startGame() {
		super.startGame();
	    startTimer();  // Timer starten!
	}

}
