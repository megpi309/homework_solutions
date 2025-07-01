package Hangman;

import javax.swing.SwingUtilities;

class TimerRunnable implements Runnable{

	   private final Timer game;

	    public TimerRunnable(Timer game) {
	        this.game = game;
	    }

	    @Override
	    public void run() {
	        while (game.timeLeft > 0 && game.timerRunning) {
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                return;
	            }

	            game.timeLeft--;

	            SwingUtilities.invokeLater(() -> {
	                game.timeLabel.setText("Time left: " + game.timeLeft);
	            });
	        }

	        if (game.timeLeft <= 0 && game.timerRunning) {
	            game.timerRunning = false;
	            SwingUtilities.invokeLater(() -> game.endGame(false));
	        }
	   }
}
