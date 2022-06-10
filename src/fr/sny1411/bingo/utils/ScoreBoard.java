package fr.sny1411.bingo.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import fr.sny1411.bingo.Plugin;
import net.md_5.bungee.api.ChatColor;

public class ScoreBoard {
	private Game game;
	private Plugin plugin;
	
	public ScoreBoard(Game game, Plugin plugin) {
		this.game = game;
		this.plugin = plugin;
	}
	
	public void createScoreBoard() {
		BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
			
			@Override
			public void run() {
				for (Player player : Bukkit.getOnlinePlayers()) {
					Scoreboard board = game.plugin.manager.getNewScoreboard();
					Objective objective = board.registerNewObjective("test", "dummy", "§6§lBINGO");
					objective.setDisplaySlot(DisplaySlot.SIDEBAR);
					Score score4 = objective.getScore("§6§l»§f§l§m                 §6§l«" + ChatColor.RESET);
					score4.setScore(8);
					Score score5 = objective.getScore("  ");
					score5.setScore(7);
					Score score = objective.getScore("  Equipes §7: §e" + game.teams.nombreTeams);
					score.setScore(6);   
					if (game.modeVictoire.equalsIgnoreCase("Bingo")) {         
			            Score score1 = objective.getScore("  Bingos §7: §e" + game.teams.nbreBingoValid.get(game.teams.findTeamPlayer(player)));
			            score1.setScore(5);
					} else {  
			            Score score1 = objective.getScore("  Défis §7: §e" + game.teams.nbreDefiValid.get(game.teams.findTeamPlayer(player)));
			            score1.setScore(5);
					}      
		            Score score2 = objective.getScore("  Mode §7: §e" + game.modeJeu);
		            score2.setScore(4);
		            Score score6 = objective.getScore("   ");
		            score6.setScore(3);
		            if (game.timeGameHour == 0) {
		            	String temps = "";
		            	int seconds = game.timer.seconds;
		            	int minutes = game.timer.minutes;
		            	if (minutes < 10) {
		            		temps += ("0" + minutes);
		            	} else {
		            		temps += (minutes);
		            	}
		            	if (seconds < 10) {
		            		temps += (":0" + seconds);
		            	} else {
		            		temps += (":" + seconds);
		            	}
		            	Score score3 = objective.getScore("  Durée §7: §e" + temps);
		            	score3.setScore(2);
		            } else { 
		            	String temps = "";
		            	int seconds = game.timer.seconds;
		            	int minutes = game.timer.minutes;
		            	if (minutes < 10) {
		            		temps += (":0" + minutes);
		            	} else {
		            		temps += (":" + minutes);
		            	}
		            	if (seconds < 10) {
		            		temps += (":0" + seconds);
		            	} else {
		            		temps += (":" + seconds);
		            	}
		            	Score score3 = objective.getScore("  Durée §7: §e0"+ game.timer.hours + temps);
		            	score3.setScore(2);
		            }
		            Score score7 = objective.getScore(" ");
		            score7.setScore(1);
		            Score score8 = objective.getScore("§6§l»§f§l§m                 §6§l«");
					score8.setScore(0);
		            player.setScoreboard(board);  
				}
			}
		}, 0L, 20L);
		
		game.plugin.listTask.add(task);
		
	}
}
