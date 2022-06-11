package fr.sny1411.bingo.utils;

import java.util.concurrent.TimeUnit;

public class Timer {
	public Integer seconds = 0;
	public Integer minutes = 0;
	public Integer hours = 0;
	private Game game;
	public boolean timerRun = false;
	
	public Timer(Game game) {
		this.game = game;
	}
	public void startTimer () {
		this.timerRun = true;
		while ((hours < game.timeGameHour || minutes < game.timeGameMinutes) && timerRun) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (seconds < 59) {
				seconds++;
			} else {
				seconds = 0;
				if (minutes < 59) {
					minutes++;
				} else {
					minutes = 0;
					hours++;
				}
			}
		}
		game.finDuJeu();
	}
	public void reset() {
		this.timerRun = false;
		this.seconds = 0;
		this.minutes = 0;
		this.hours = 0;
	}
}
