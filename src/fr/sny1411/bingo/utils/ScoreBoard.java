package fr.sny1411.bingo.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import fr.sny1411.bingo.Plugin;

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
					Objective objective = board.registerNewObjective("test", "dummy", "Bingo");
					objective.setDisplaySlot(DisplaySlot.SIDEBAR);
					Score score = objective.getScore("defis de la team : " + game.teams.nbreDefiValid.get(game.teams.findTeamPlayer(player)));
					score.setScore(3);            
		            Score score1 = objective.getScore(" ");
		            score1.setScore(2);        
		            Score score2 = objective.getScore(" ");
		            score2.setScore(1);                        
		            Score score3 = objective.getScore(game.timer.hours.toString() + ":" + game.timer.minutes.toString() + ":" + game.timer.seconds.toString());
		            score3.setScore(0);
		            player.setScoreboard(board);  
				}
			}
		}, 0L, 20L);
		
		game.plugin.listTask.add(task);
		
	}
}
