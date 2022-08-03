package fr.sny1411.bingo.utils;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;

import fr.sny1411.bingo.Plugin;

public class Timer {
	public Integer seconds = 0;
	public Integer minutes = 0;
	public Integer hours = 0;
	private Game game;
	public boolean timerRun = false;
	private Plugin plugin;
	
	public Timer(Plugin plugin, Game game) {
		this.game = game;
		this.plugin = plugin;
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
			
			if (game.eventDefiBonus.equalsIgnoreCase("On")) {
				if (game.eventDefisBonus.timeEvents.size() != 0) {
					if ((game.eventDefisBonus.timeEvents.get(0)*60) == (hours*3600 + minutes*60 + seconds)) {
						game.eventDefisBonus.newEvent();
					}
				}
			}
		}
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				game.finDuJeu();
			}
		});

	}
	public void reset() {
		this.timerRun = false;
		this.seconds = 0;
		this.minutes = 0;
		this.hours = 0;
	}
}
