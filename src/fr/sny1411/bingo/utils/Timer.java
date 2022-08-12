package fr.sny1411.bingo.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	private Integer timeOrageLaunch; // en minutes
	private List<Integer> timeMessageFin = new ArrayList<>(Arrays.asList(110*60,115*60,117*60,118*60,119*60,119*60+30,119*60+45,119*60+50,119*60+55,119*60+57,119*60+58,119*60+59,120)); // en secondes
	private List<String> messagesFin = new ArrayList<>(Arrays.asList("§7[§eBINGO§7] §f10 minutes restantes",
																	 "§7[§eBINGO§7] §f5 minutes restantes",
																	 "§7[§eBINGO§7] §f3 minutes restantes",
																	 "§7[§eBINGO§7] §f2 minutes restantes",
																	 "§7[§eBINGO§7] §f1 minute restante",
																	 "§7[§eBINGO§7] §f30 secondes restantes",
																	 "§7[§eBINGO§7] §f15 secondes restantes",
																	 "§7[§eBINGO§7] §f10 secondes restantes",
																	 "§7[§eBINGO§7] §f5 secondes restantes",
																	 "§7[§eBINGO§7] §f3 secondes restantes",
																	 "§7[§eBINGO§7] §f2 secondes restantes",
																	 "§7[§eBINGO§7] §f1 secondes restantes",
																	 "\uE005"));
	
	public Timer(Plugin plugin, Game game) {
		this.game = game;
		this.plugin = plugin;
	}
	public void startTimer () {
		int timeInsecond;
		int compteurMsgFin = 0;
		timeOrageLaunch = (int)(Math.random() * ((105 - 60) + 1)) + 60;
		System.out.println("Orage : " + timeOrageLaunch);
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
			
			timeInsecond = (hours*3600 + minutes*60 + seconds);
			
			if (timeMessageFin.get(compteurMsgFin) == timeInsecond) {
				Bukkit.getServer().broadcastMessage(messagesFin.get(compteurMsgFin));
				compteurMsgFin++;
			}
			
			if (timeOrageLaunch*60 == timeInsecond) {
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					
					@Override
					public void run() {
						System.out.println("PLUIE");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "weather thunder");
						Bukkit.getServer().getWorld("world").setWeatherDuration(18000); // 15 minutes (en ticks)
					}
				});
			}
			
			if (game.eventDefiBonus.equalsIgnoreCase("On")) {
				if (game.eventDefisBonus.timeEvents.size() != 0) {
					if ((game.eventDefisBonus.timeEvents.get(0)*60) == timeInsecond) {
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
