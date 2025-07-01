package Hangman_Interface;

public interface GameLogic {
	void handleGuess(char guess);
	boolean hasWon();
	void endGame(boolean won);
}
