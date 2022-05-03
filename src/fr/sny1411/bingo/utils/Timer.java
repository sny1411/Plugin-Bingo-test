package fr.sny1411.bingo.utils;

import java.util.concurrent.TimeUnit;

public class Timer {
	public int seconds = 0;
	private Game game;
	private boolean timerRun = false;
	
	public Timer(Game game) {
		this.game = game;
	}
	public void startTimer () {
		this.timerRun = true;
		while (seconds >= game.timeGame && timerRun) {
			seconds++;
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void reset() {
		this.seconds = 0;
		this.timerRun = false;
	}
}
