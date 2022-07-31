package fr.sny1411.bingo.utils;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;

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
			if (game.timeGameHour == (hours - 1) && game.timeGameMinutes == (minutes + 50) && seconds == 0) {
				Bukkit.broadcastMessage("§7[§eBINGO§7] §f10 minutes restantes");
			} else if (game.timeGameHour == (hours - 1) && game.timeGameMinutes == (minutes + 55) && seconds == 0) {
				Bukkit.broadcastMessage("§7[§eBINGO§7] §f5 minutes restantes");
			} else if (game.timeGameHour == (hours - 1) && game.timeGameMinutes == (minutes + 59) && seconds == 0) {
				Bukkit.broadcastMessage("§7[§eBINGO§7] §f1 minute restante");
			} else if (game.timeGameHour == (hours - 1) && game.timeGameMinutes == (minutes + 59) && seconds == 30) {
				Bukkit.broadcastMessage("§7[§eBINGO§7] §f30 secondes restantes");
			} else if (game.timeGameHour == (hours - 1) && game.timeGameMinutes == (minutes + 59) && seconds == 50) {
				Bukkit.broadcastMessage("§7[§eBINGO§7] §f10 secondes restantes");
			} else if (game.timeGameHour == (hours - 1) && game.timeGameMinutes == (minutes + 59) && seconds == 57) {
				Bukkit.broadcastMessage("§7[§eBINGO§7] §f3 secondes restantes");
			} else if (game.timeGameHour == (hours - 1) && game.timeGameMinutes == (minutes + 59) && seconds == 57) {
				Bukkit.broadcastMessage("§7[§eBINGO§7] §f2 secondes restantes");
			} else if (game.timeGameHour == (hours - 1) && game.timeGameMinutes == (minutes + 59) && seconds == 57) {
				Bukkit.broadcastMessage("§7[§eBINGO§7] §f1 seconde restante");
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
