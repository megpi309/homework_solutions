package Hangman;

import javax.swing.JLabel;

public class Timer extends Game {
	
	protected Thread timerThread;
    protected boolean timerRunning;

    @Override
    protected void startGame() {
        super.startGame();  // Initialisiert Spielzustand
        startTimer();       // Starte den Timer
    }

    protected void startTimer() {
        timerRunning = true;

        if (timerThread != null && timerThread.isAlive()) {
            timerThread.interrupt();
        }

        timerThread = new Thread(new TimerRunnable(this));
        timerThread.start();
    }
}
